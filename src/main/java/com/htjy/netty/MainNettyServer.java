package com.htjy.netty;

/**
 * Created By willz
 * Date : 2020/10/27
 * Time: 16:58
 */
public class MainNettyServer {
    private static int port = 8007;

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer(port);
        chatServer.run();
    }
}
