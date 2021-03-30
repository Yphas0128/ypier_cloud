package cn.ypier.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 处理器
 * @Author Ypier
 */

public class HelloNettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 通过socketChannel去获得对应的管道
        ChannelPipeline channelPipeline = socketChannel.pipeline();

        //响应到客户端做编码
        channelPipeline.addLast("HttpServerCodec", new HttpServerCodec());
        // 添加自定义handler
        channelPipeline.addLast("customHandler", new CustomHandler());

    }
}
