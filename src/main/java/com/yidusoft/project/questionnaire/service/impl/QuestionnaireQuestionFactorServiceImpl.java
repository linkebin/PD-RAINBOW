package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionFactorMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestionFactor;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionFactorService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireQuestionFactorServiceImpl extends AbstractService<QuestionnaireQuestionFactor> implements QuestionnaireQuestionFactorService {
    @Resource
    private QuestionnaireQuestionFactorMapper questionnaireQuestionFactorMapper;

}
