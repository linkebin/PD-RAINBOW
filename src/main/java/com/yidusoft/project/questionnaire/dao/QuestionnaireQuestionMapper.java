package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;

import java.util.List;
import java.util.Map;

public interface QuestionnaireQuestionMapper extends Mapper<QuestionnaireQuestion> {
    //分页条件查询问题
    List<QuestionnaireQuestion> questionListByPage(QuestionnaireQuestion questionnaireQuestion);
   //问卷或量表  添加问题 查询没有添加的问题
    List<QuestionnaireQuestion> findQuestionBYid(  Map<String,Object> map);
    //查询问卷相关的问题
    List<QuestionnaireQuestion> findQuestionForQuestionnaire(String id);
}