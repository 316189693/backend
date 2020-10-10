package com.htjy.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EpollServer {
   static Logger logger = LoggerFactory.getLogger(EpollServer.class);
    public static void main(String[] args) throws Exception {
        String port = System.getProperty("port", "8007");
        logger.info("port:"+port);
        logger.info("isUseEpoll:"+System.getProperty("isUserEpoll","true"));
        EventLoopGroup bossGroup;
        EventLoopGroup workerGroup;
        Class          clazz;

        if (useEpoll(true)) {
            bossGroup = new EpollEventLoopGroup(1);
            workerGroup = new EpollEventLoopGroup();
            clazz = EpollServerSocketChannel.class;
        }else{
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();
            clazz = NioServerSocketChannel.class;
        }

        try {
            ServerBootstrap b = new ServerBootstrap();
            logger.info("[socket Type]: {}", clazz.getSimpleName());
            b.group(bossGroup, workerGroup)
                    .channel(clazz)
                    .option(ChannelOption.SO_BACKLOG, 10001)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                public void initChannel(SocketChannel ch) throws Exception {
                                    ChannelPipeline p = ch.pipeline();
                                    p.addLast(new HttpRequestDecoder());
                                    p.addLast(new LengthFieldBasedFrameDecoder(1024,0,2,0,2));
                                    p.addLast(new SimpleChannelInboundHandler(){
                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            PooledByteBufAllocator allocator = new PooledByteBufAllocator();
                                            ByteBuf buf = allocator.buffer(1024);
                                            if (msg instanceof DefaultHttpRequest) {
                                                buf.setBytes(0,((DefaultHttpRequest)msg).uri().getBytes());
                                            } else {
                                                buf.setBytes(0, "test".getBytes());
                                            }

                                            ctx.channel().writeAndFlush(buf.retain());
                                        }
                                    });

                        }
                    });

            ChannelFuture f = b.bind(Integer.valueOf(port)).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    private static boolean useEpoll(boolean isUseEpoll) {
        if(isUseEpoll){
            return Epoll.isAvailable();
        }else{
            return false;
        }
    }
}