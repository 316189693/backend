package com.htjy.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created By willz
 * Date : 2020/10/27
 * Time: 16:59
 */
public class ChatServer{
    private int port;
    public ChatServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup works = new NioEventLoopGroup(4);
        try{
            ServerBootstrap server = new ServerBootstrap();
            server.group(boss, works)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChatServerHandler(),new DiscardServerHandler());
                        }
                    });
            ChannelFuture channelFuture = server.bind(this.port).sync();
            if (channelFuture.isSuccess()){
                System.out.println("Server started");
            }
            channelFuture.channel().closeFuture().sync();
            System.out.println("Server will closed");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            works.shutdownGracefully();
            System.out.println("Server closed");
        }
    }
}
