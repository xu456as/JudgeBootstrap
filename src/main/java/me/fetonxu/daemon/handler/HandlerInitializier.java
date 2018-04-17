package me.fetonxu.daemon.handler;

import io.netty.channel.Channel;
import me.fetonxu.netty.handler.ChannelHandlerAppender;
import me.fetonxu.netty.handler.HttpServerUrlHandler;

public class HandlerInitializier implements ChannelHandlerAppender {

    @Override public void handle(Channel channel) {
        HttpServerUrlHandler mainHandler = new HttpServerUrlHandler();

        mainHandler.register("/run_judge", new JudgeBootstrapHandler())
            .register("/ping", new PingHandler());

        channel.pipeline().addLast(mainHandler);
    }
}
