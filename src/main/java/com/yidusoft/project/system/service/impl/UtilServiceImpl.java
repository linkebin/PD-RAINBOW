package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.UtilMapper;
import com.yidusoft.project.system.service.UtilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
@Service
@Transactional
public class UtilServiceImpl implements UtilService {
    @Resource
    private UtilMapper utilMapper;

    @Override
    public String getCode(String codeName) {
        return utilMapper.getCode(codeName);
    }
}
