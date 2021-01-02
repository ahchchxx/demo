package com.example.test1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

//ChannelInboundHandlerAdapter实现自ChannelInboundHandler
//ChannelInboundHandler提供了不同的事件处理方法来重写
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    /*
     * @说明:该方法用于接收从客户端接收的信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            System.out.println(msg);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}