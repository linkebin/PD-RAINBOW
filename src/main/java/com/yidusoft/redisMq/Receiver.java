package com.yidusoft.redisMq;

import com.alibaba.fastjson.JSONObject;
import com.yidusoft.project.monitor.domain.LoginLog;
import com.yidusoft.project.monitor.service.LoginLogService;
import com.yidusoft.utils.IpAddressUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
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
        if("登录日志".equals(sysMessage.getMessageType())){
            loginLog(sysMessage);
        }
        latch.countDown();

    }

    /**
     * 记录登录 日志
     */
    public  void loginLog(SysMessage sysMessage){
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginId(UUID.randomUUID().toString());
        loginLog.setUserId(sysMessage.getRecid());
        loginLog.setUserName(sysMessage.getMessageTitle());
        loginLog.setUserAccount(sysMessage.getMessage());
        loginLog.setAccountType(sysMessage.getDeleted());
        String ip = IpAddressUtils.getIpAddr(); //获取IP地址
        loginLog.setLoginIp(ip);
        loginLog.setLoginTime(new Date());
        loginLog.setLoginType("网页登录");
        loginLog.setLoginAddr("未知地点");

        try{
            if (!"未知IP".equals(ip)){
                Map<String,Object> map = IpAddressUtils.getAddress("ip="+ip, "utf-8");
                StringBuffer buffer = new StringBuffer();
                buffer.append(map.get("region").toString()+"->");
                if (!"".equals(map.get("city").toString())){
                    buffer.append(map.get("city").toString());
                }
                if (!"".equals(map.get("county").toString())){
                    buffer.append(map.get("county").toString());
                }
                loginLog.setLoginAddr(buffer.toString());
            }
            loginLogService.insertLoginInfo(loginLog);
        }catch (Exception e){
            logger.info("插入登录日志失败");
            e.printStackTrace();
        }
    }
}
