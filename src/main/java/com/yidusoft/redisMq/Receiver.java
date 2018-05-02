package com.yidusoft.redisMq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yidusoft.project.monitor.domain.OperLog;
import com.yidusoft.project.monitor.service.OperLogService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.utils.LoginLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    private LoginLog loginLog;

    @Autowired
    private OperLogService operLogService;


    public void receiveMessage(String  message) {
        JSONObject object = JSONObject.parseObject(message);
        Message msg  = JSONObject.toJavaObject(object,Message.class);
        if("登录日志".equals(msg.getType())){
            SecUser user = JSONObject.toJavaObject((JSON) msg.getObject(),SecUser.class);
            logger.info("存储用户的登录记录");
            loginLog.LoginLog(user);
        }
        else if ("操作日志".equals(msg.getType())){
            OperLog operLog = JSONObject.toJavaObject((JSON) msg.getObject(),OperLog.class);
            logger.info("存储用户的操作记录");
            operLogService.save(operLog);
        }
        latch.countDown();

    }

}
