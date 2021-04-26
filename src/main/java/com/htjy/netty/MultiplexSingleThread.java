package com.htjy.netty;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiplexSingleThread {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(8080));
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        while(true) {
            while(selector.select(20) > 0) {
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while(iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()){
                        ServerSocketChannel server1 = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel =  server1.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("connect " + socketChannel.getRemoteAddress().toString());
                    } else if (key.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2048);
                        SocketChannel socketChannel2 = (SocketChannel)key.channel();
                        while (true) {
                            int nums = socketChannel2.read(byteBuffer);
                            if (nums > 0) {
                                byteBuffer.flip();
                                byte[] bytes = new byte[nums];
                                byteBuffer.get(bytes);
                                String o = new String(bytes);
                                System.out.println(o);
                                socketChannel2.register(selector, SelectionKey.OP_WRITE, o);
                            } else if (nums == 0) {
                                byteBuffer.clear();
                                break;
                            } else {
                                socketChannel2.close();
                                break;
                            }
                        }

                    } else if (key.isWritable()) {
                        SocketChannel socketChannel3 = (SocketChannel)key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.wrap(((String)key.attachment()).getBytes());
                        socketChannel3.write(byteBuffer);
                        socketChannel3.register(selector, SelectionKey.OP_READ);
                        break;
                    }
                }
            }
        }

    }
}
