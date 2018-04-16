package me.fetonxu.daemon.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ele.me.hackathon.tank.GameEngine;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import me.fetonxu.netty.handler.HttpRequestHandler;
import me.fetonxu.netty.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JudgeBootstrapHandler implements HttpRequestHandler {

    private static Logger logger = LoggerFactory.getLogger(JudgeBootstrapHandler.class);

    @Override public void get(ChannelHandlerContext context, HttpRequest request,
        Map<String, List<String>> queryStringMap) throws Exception {

        context.writeAndFlush(ResponseUtil.simpleResponse(HttpResponseStatus.BAD_GATEWAY, "method not support"));
    }

    @Override public void post(ChannelHandlerContext context, HttpRequest request,
        Map<String, List<String>> queryStringMap, ByteBuf requestBody) throws Exception {

        String result = "0;default";

        try{
            String rawString = new String(ByteBufUtil.getBytes(requestBody), "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(rawString);
            List<String> list = new ArrayList<String>(){{
                jsonObject.getString("mapFile");
                jsonObject.getString("noOfTanks");
                jsonObject.getString("tankSpeed");
                jsonObject.getString("tankHP");
                jsonObject.getString("tankScore");
                jsonObject.getString("flagScore");
                jsonObject.getString("maxRound");
                jsonObject.getString("roundTimeout");
                jsonObject.getString("playerAAddres");
                jsonObject.getString("playerBAddres");
                jsonObject.getString("aId");
                jsonObject.getString("bId");
                jsonObject.getString("timestamp");
            }};

            runJudge((String[]) list.toArray());
            result = "1;submitted";
        }catch (Exception e){
            result = "0;error occurred";
            logger.error(String.format("error: %s", e));
        }
        context.writeAndFlush(ResponseUtil.simpleResponse(HttpResponseStatus.OK, result));
    }
    private static void runJudge(String[] args) throws Exception{
        ExecutorService singleThreadpool = Executors.newSingleThreadExecutor();
        singleThreadpool.submit(new Runnable() {
            @Override public void run() {
                try {
                    GameEngine.main(args);
                }catch (Exception e){
                    logger.error(String.format("error: %s", e));
                }
            }
        });
    }
}
