package com.yeafel.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author Yeafel
 * 服务初始化器里面其实就是放了很多ChannelHandler。
 * 2019/7/24 9:58
 * Do or Die,To be a better man!
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     *  连接一旦被注册之后，创建后这个方法立刻被调用，是个初始化回调方法。
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //一个管道中可以有很多ChannelHandler（拦截器）,每个ChannelHandler可以做很多事情
        ChannelPipeline pipeline = ch.pipeline();
        //HttpServerCodec对于web的请求和相应的编解码用的
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        //紧接着创建我们自己定义的Handler
        pipeline.addLast("testHttpServerHandler",new TestHttpServerHandler());

    }
}
