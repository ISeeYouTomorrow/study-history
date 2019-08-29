package com.test.concurrent.study.netty.chapter1.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @ClassName TimeClientHandler
 * @Author @lvxile
 * @Date 2019/8/23 15:18
 * @Description TODO
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf)msg;
        try{
            long currentTime = (m.readUnsignedInt()-2208988800L)*1000;
            System.out.println(new Date(currentTime));
            ctx.close();
        }finally {
            m.release();
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
