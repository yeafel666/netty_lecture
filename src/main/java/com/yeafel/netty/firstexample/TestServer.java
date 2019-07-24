package com.yeafel.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Yeafel
 * 2019/7/24 9:49
 * Do or Die,To be a better man!
 */
public class TestServer {
    public static void main(String[] args) throws Exception {
        //两个死循环。老板和工人,老板只是获取连接，什么都没做，后续处理给工人完成。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //netty提供：很轻松的启动一个服务管道
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new TestServerInitializer());

            //启动的时候绑定到某个端口上
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            //定义关闭的监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
