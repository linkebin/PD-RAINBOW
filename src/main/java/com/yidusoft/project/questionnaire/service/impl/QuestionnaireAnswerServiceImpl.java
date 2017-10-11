package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.QuestionnaireAnswerMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.service.QuestionnaireAnswerService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireAnswerServiceImpl extends AbstractService<QuestionnaireAnswer> implements QuestionnaireAnswerService {
    @Resource
    private QuestionnaireAnswerMapper questionnaireAnswerMapper;

}
