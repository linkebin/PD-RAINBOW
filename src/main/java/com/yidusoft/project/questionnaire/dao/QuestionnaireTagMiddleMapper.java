package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTagMiddle;

public interface QuestionnaireTagMiddleMapper extends Mapper<QuestionnaireTagMiddle> {

    //删除绑定的标签问卷
    QuestionnaireTagMiddle  deleteQuestionnaireTagMiddle(QuestionnaireTagMiddle questionnaireTagMiddle);
}