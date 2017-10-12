package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;

import java.util.List;

public interface QuestionnaireQuestionMapper extends Mapper<QuestionnaireQuestion> {
    //分页条件查询问题
    List<QuestionnaireQuestion> questionListByPage(QuestionnaireQuestion questionnaireQuestion);
}