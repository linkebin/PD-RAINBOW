package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.QuestionnaireTagMiddleMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTagMiddle;
import com.yidusoft.project.questionnaire.service.QuestionnaireTagMiddleService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireTagMiddleServiceImpl extends AbstractService<QuestionnaireTagMiddle> implements QuestionnaireTagMiddleService {
    @Resource
    private QuestionnaireTagMiddleMapper questionnaireTagMiddleMapper;

}
