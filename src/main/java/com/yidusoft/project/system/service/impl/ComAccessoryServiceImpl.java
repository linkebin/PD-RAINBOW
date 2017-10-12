package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.ComAccessoryMapper;
import com.yidusoft.project.system.domain.ComAccessory;
import com.yidusoft.project.system.service.ComAccessoryService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class ComAccessoryServiceImpl extends AbstractService<ComAccessory> implements ComAccessoryService {
    @Resource
    private ComAccessoryMapper comAccessoryMapper;

}
