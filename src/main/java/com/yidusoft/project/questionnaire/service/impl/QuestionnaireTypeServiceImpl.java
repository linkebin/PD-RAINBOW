package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.QuestionnaireTypeMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireType;
import com.yidusoft.project.questionnaire.service.QuestionnaireTypeService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireTypeServiceImpl extends AbstractService<QuestionnaireType> implements QuestionnaireTypeService {
    @Resource
    private QuestionnaireTypeMapper questionnaireTypeMapper;

}
