package me.fetonxu.daemon.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import me.fetonxu.netty.handler.HttpRequestHandler;
import me.fetonxu.netty.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUploadHandler implements HttpRequestHandler {

    private static Logger logger = LoggerFactory.getLogger(MapUploadHandler.class);

    @Override public void get(ChannelHandlerContext context, HttpRequest request,
        Map<String, List<String>> queryStringMap) throws Exception {
        context.writeAndFlush(
            ResponseUtil.simpleResponse(HttpResponseStatus.BAD_GATEWAY, "method not support"));
    }

    @Override public void post(ChannelHandlerContext context, HttpRequest request,
        Map<String, List<String>> queryStringMap, ByteBuf requestBody) throws Exception {
        String result = "0;default";

        try{
            String mapName = queryStringMap.get("mapName").get(0);
            File outputFile = new File(System.getProperty("user.dir") + "/maps", mapName);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            FileChannel channel = outputStream.getChannel();
            channel.write(requestBody.nioBuffer());
            outputStream.flush();
            outputStream.close();

            result = "1;handled";
        }catch (Exception e){
            result = "0;error occurred";
            logger.error(String.format("error: %s", e));
        }
        context.writeAndFlush(ResponseUtil.simpleResponse(HttpResponseStatus.OK, result));
    }
}
