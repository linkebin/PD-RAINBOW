package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.QuestionnaireSceneMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireScene;
import com.yidusoft.project.questionnaire.service.QuestionnaireSceneService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireSceneServiceImpl extends AbstractService<QuestionnaireScene> implements QuestionnaireSceneService {
    @Resource
    private QuestionnaireSceneMapper questionnaireSceneMapper;

}