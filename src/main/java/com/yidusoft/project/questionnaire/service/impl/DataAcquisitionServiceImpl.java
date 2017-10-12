package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.DataAcquisitionMapper;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class DataAcquisitionServiceImpl extends AbstractService<DataAcquisition> implements DataAcquisitionService {
    @Resource
    private DataAcquisitionMapper dataAcquisitionMapper;

}
