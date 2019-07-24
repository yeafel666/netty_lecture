package com.yeafel.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * @author Yeafel
 * 2019/7/24 10:06
 * Do or Die,To be a better man!
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取客户端发来的请求，并且给客户端返回响应的方法。
     * 我们需要在这里面构造响应返回给客户端
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println(msg.getClass());

        System.out.println(ctx.channel().remoteAddress());

        if (msg instanceof HttpRequest) {

            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println("请求方法名:" + httpRequest.method().name());
            URI uri = new URI(httpRequest.uri());

            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求favicon.ico");
            }

            //Netty中传递字节数据的容器。，里面装的是我们需要给客户端返回的东西，此概念非常重要。
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            //构造响应对象，netty不用servlet的那一套，有自己的响应对象
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK);
            //设置响应类型
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            //设置响应长度
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            //返回给客户端
            //如果调用ctx.write(response)方法并不会真正返回到客户端，只会被放进缓冲区里。
            ctx.writeAndFlush(response);

            ctx.channel().close();
        }
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel_Unregistered");
        super.channelUnregistered(ctx);
    }
}
