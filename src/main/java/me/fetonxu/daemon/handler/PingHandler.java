package me.fetonxu.daemon.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import me.fetonxu.netty.handler.HttpRequestHandler;
import me.fetonxu.netty.util.ResponseUtil;

import java.util.List;
import java.util.Map;

public class PingHandler implements HttpRequestHandler {
    @Override public void get(ChannelHandlerContext context, HttpRequest request,
        Map<String, List<String>> queryStringMap) throws Exception {
        context.writeAndFlush(ResponseUtil.simpleResponse(HttpResponseStatus.OK, "OK"));
    }

    @Override public void post(ChannelHandlerContext context, HttpRequest request,
        Map<String, List<String>> queryStringMap, ByteBuf requestBody) throws Exception {
        context.writeAndFlush(ResponseUtil.simpleResponse(HttpResponseStatus.OK, "OK"));
    }
}
