package com.yidusoft.project.monitor.service.impl;

import com.yidusoft.project.monitor.dao.LoginLogMapper;
import com.yidusoft.project.monitor.domain.LoginLog;
import com.yidusoft.project.monitor.service.LoginLogService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class LoginLogServiceImpl extends AbstractService<LoginLog> implements LoginLogService {
    @Resource
    private LoginLogMapper loginLogMapper;

}
