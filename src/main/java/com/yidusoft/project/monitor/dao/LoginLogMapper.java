package com.yidusoft.project.monitor.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.monitor.domain.LoginLog;

import java.util.List;

public interface LoginLogMapper extends Mapper<LoginLog> {

    /**
     * 根据参数查询登录记录
     * @return
     */
    List<LoginLog> findLoginLogByParameter(LoginLog loginLog);
}