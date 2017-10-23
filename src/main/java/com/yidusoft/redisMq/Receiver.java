package com.yidusoft.redisMq;

import com.alibaba.fastjson.JSONObject;
import com.yidusoft.project.monitor.service.LoginLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * Created by linkb on 2017/10/16.
 */
@Component
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Resource
    private LoginLogService loginLogService;

    public void receiveMessage(String  message) {
        JSONObject object = JSONObject.parseObject(message);
        SysMessage sysMessage = JSONObject.toJavaObject(object, SysMessage.class);
        latch.countDown();

    }

}
