package com.yidusoft.project.monitor.service.impl;

import com.yidusoft.project.monitor.dao.OperLogMapper;
import com.yidusoft.project.monitor.domain.OperLog;
import com.yidusoft.project.monitor.service.OperLogService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class OperLogServiceImpl extends AbstractService<OperLog> implements OperLogService {
    @Resource
    private OperLogMapper operLogMapper;

}
