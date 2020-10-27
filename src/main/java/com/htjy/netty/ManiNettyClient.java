package com.htjy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created By willz
 * Date : 2020/10/27
 * Time: 16:58
 */
public class ManiNettyClient {
    private static String host = "127.0.0.1";
    private static int port = 8007;


    public static void main(String[] args) {
        ChatClient client = new ChatClient(host, port);
        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                client.run();
            }
        });
        clientThread.start();

       Channel channel = null;
        boolean isStart = false;
        while (!isStart){
            if (null != client.getChannel()){
                channel = client.getChannel();
                isStart = true;
            }
        }

        String helo = "hello, this is client.";
        ByteBuf byteBuf = Unpooled.wrappedBuffer(helo.getBytes());
        channel.writeAndFlush(byteBuf);
        for (int i = 0; i < 10 ; i++) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            channel.writeAndFlush(Unpooled.wrappedBuffer(text.getBytes()));
        }
    }
}
