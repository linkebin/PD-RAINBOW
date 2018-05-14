package com.yidusoft.utils;

import com.yidusoft.project.monitor.service.LoginLogService;
import com.yidusoft.project.system.domain.SecUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by linkb on 2018/5/2.
 */
@Service
public class LoginLog {


    @Autowired
    LoginLogService loginLogService;
    Logger logger = LoggerFactory.getLogger(LoginLog.class);

    public void LoginLog(SecUser user) {
        com.yidusoft.project.monitor.domain.LoginLog loginLog = new com.yidusoft.project.monitor.domain.LoginLog();
        loginLog.setLoginId(UUID.randomUUID().toString());
        loginLog.setUserId(user.getId());
        loginLog.setUserName(user.getUserName());
        loginLog.setUserAccount(user.getAccount());
        loginLog.setAccountType(user.getAccountType());
        loginLog.setLoginIp(user.getIp());
        loginLog.setLoginTime(new Date());
        loginLog.setLoginType("网页登录");
        loginLog.setLoginAddr("未知地点");
        try {
            if (!"未知IP".equals(user.getIp())) {
                Map<String, Object> map = IpAddressUtils.getAddress("ip=" + user.getIp(), "utf-8");
                StringBuffer buffer = new StringBuffer();
                buffer.append(map.get("region").toString() + "->");
                if (!"".equals(map.get("city").toString())) {
                    buffer.append(map.get("city").toString());
                }
                if (!"".equals(map.get("county").toString())) {
                    buffer.append(map.get("county").toString());
                }
                loginLog.setLoginAddr(buffer.toString());
            }
        } catch (Exception e) {
            logger.info("获取地点失败");
        }finally {
            loginLogService.insertLoginInfo(loginLog);
        }
    }
}