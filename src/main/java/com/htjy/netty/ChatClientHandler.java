package com.htjy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created By willz
 * Date : 2020/10/27
 * Time: 16:59
 */
public class ChatClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg; // 将消息转化成bytebuf
        try {
            System.out.println("Received: " + m.toString(Charset.defaultCharset()));
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.writeAndFlush("client received:: " + m.toString(Charset.defaultCharset()));
        } finally {
            m.release();
        }
    }

    /**
     * 连接成功后，自动执行该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /**
         * 往服务端发送消息
         * 消息格式必须是ByteBuf才行!!!!!
         * 如果是其他格式服务端是接收不到的!!!!
         */
        String helo = "Hello, this is Client";
        ByteBuf byteBuf = Unpooled.wrappedBuffer(helo.getBytes());
        ctx.channel().writeAndFlush(byteBuf);
        System.out.println("Connected!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
