package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestionFactor;

import java.util.List;

public interface QuestionnaireQuestionFactorMapper extends Mapper<QuestionnaireQuestionFactor> {

    //查询问卷相关的问题因子
  List<QuestionnaireQuestionFactor> findQuestionnaireQuestionFactor(String id);
}