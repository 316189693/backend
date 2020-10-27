package com.htjy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created By willz
 * Date : 2020/10/27
 * Time: 16:59
 */
public class ChatServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Server received:" + buf.toString(CharsetUtil.UTF_8));
        ByteBuf res = Unpooled.wrappedBuffer(new String("Server received, please confirm:"+ buf.toString(CharsetUtil.UTF_8)).getBytes());
        ctx.writeAndFlush(res);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client Connected!");
        ctx.writeAndFlush("Hello, this is Server");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        cause.printStackTrace();
        ctx.close();
    }
}
