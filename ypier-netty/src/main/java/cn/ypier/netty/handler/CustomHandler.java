package cn.ypier.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @Author Ypier
 */
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        Channel channel = ctx.channel();
        // 判断msg是否为一个HttpRequest的请求类型
        if(msg instanceof HttpRequest){


        }

    }
}
