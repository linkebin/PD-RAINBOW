package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.GaugeQuestionFactorMapper;
import com.yidusoft.project.questionnaire.domain.GaugeQuestionFactor;
import com.yidusoft.project.questionnaire.service.GaugeQuestionFactorService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class GaugeQuestionFactorServiceImpl extends AbstractService<GaugeQuestionFactor> implements GaugeQuestionFactorService {
    @Resource
    private GaugeQuestionFactorMapper gaugeQuestionFactorMapper;

}
