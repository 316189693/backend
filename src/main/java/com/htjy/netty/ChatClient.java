package com.htjy.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created By willz
 * Date : 2020/10/27
 * Time: 16:59
 */
public class ChatClient {
    private int port;
    private String host;
    private Channel channel;
    public ChatClient(String host, int port){
        this.host = host;
        this.port = port;
    }



    public void run() {
        EventLoopGroup work = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChatClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(this.host, this.port).sync();
            if (future.isSuccess()){
                System.out.println("client started");
            }
            this.channel = future.channel();
            future.channel().closeFuture().sync();
            System.out.println("Client will closed");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            work.shutdownGracefully();
            System.out.println("Client closed");
        }
    }

    public Channel getChannel(){
        return this.channel;
    }
}
