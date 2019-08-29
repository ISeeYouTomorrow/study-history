package com.test.concurrent.study.netty.chapter1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName EchoClinetHandler
 * @Author @lvxile
 * @Date 2019/8/23 13:33
 * @Description TODO
 */
public class EchoClinetHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;
    public EchoClinetHandler() {

        firstMessage = Unpooled.buffer(EchoClient.SIZE);

        for (int i = 0; i < ((ByteBuf) firstMessage).capacity(); i++) {
            firstMessage.writeByte((byte) i);
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
