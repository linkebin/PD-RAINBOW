package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.GaugeTagMiddleMapper;
import com.yidusoft.project.questionnaire.domain.GaugeTagMiddle;
import com.yidusoft.project.questionnaire.service.GaugeTagMiddleService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class GaugeTagMiddleServiceImpl extends AbstractService<GaugeTagMiddle> implements GaugeTagMiddleService {
    @Resource
    private GaugeTagMiddleMapper gaugeTagMiddleMapper;

}
