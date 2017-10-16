package com.yidusoft.redisMq;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Created by linkb on 2017/8/23.
 */
@Service
public class MsgSend {

    @Autowired
    protected ApplicationContext ctx;
    private static final Logger logger = LoggerFactory.getLogger(MsgSend.class);
    public void send(SysMessage sysMessage) {
        String msgJson = JSON.toJSONString(sysMessage);
        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        CountDownLatch latch = ctx.getBean(CountDownLatch.class);
        template.convertAndSend("msgMq", msgJson);
        try {
              //发送消息连接等待中
            logger.info("发送了一条队列消息,消息内容："+sysMessage.toString());
            latch.await();
        } catch (InterruptedException e) {
            logger.info("消息发送失败...");
        }
    }

}
