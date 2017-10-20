package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireScene;

public interface QuestionnaireSceneMapper extends Mapper<QuestionnaireScene> {
    //删除问卷相关的场景
    QuestionnaireScene deleteQuestionnaireScene(QuestionnaireScene questionnaireScene);
}