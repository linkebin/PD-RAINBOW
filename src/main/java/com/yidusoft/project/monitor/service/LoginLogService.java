package com.yidusoft.project.monitor.service;
import com.yidusoft.project.monitor.domain.LoginLog;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface LoginLogService extends Service<LoginLog> {

    /**
     * 记录登录日志信息
     * @param loginLog
     */
    void insertLoginInfo(LoginLog loginLog);

    List<LoginLog> findLoginLogByParameter(LoginLog loginLog);
}
