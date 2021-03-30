package cn.ypier.netty.server;

import cn.ypier.netty.handler.HelloNettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty 使用 教程
 * @Author Ypier
 */
public class HelloNettyServer {
    /**
     * 1.构建一对 主从线程组
     * 2.定义服务启动类
     * 3.为服务器设置Channel
     * 4.设置处理从线程池的助手类初始化器
     * 5.监听启动和关闭的服务器
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 定义一对线程组（两个线程池）
         */
        // 主线程组，用于接收客户端的链接，但不做任何处理
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 定义从线程组，主线程组会把任务转给从线程组进行处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            /**
             * 服务启动类,任务分配自动处理
             */
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    // 设置NIO的双向通道
                    .channel(NioServerSocketChannel.class)
                    // 子处理器
                    .childHandler(new HelloNettyServerInitializer());

            // 绑定端口
            ChannelFuture future = serverBootstrap.bind(8888).sync();
            // 端口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //使用一种优雅的方式进行关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
