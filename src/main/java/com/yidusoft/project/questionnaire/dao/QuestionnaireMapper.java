package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.Questionnaire;

import java.util.List;

public interface QuestionnaireMapper extends Mapper<Questionnaire> {

    //分页条件查询相关的问卷信息
    List<Questionnaire> questionnaireListByPage(Questionnaire questionnaire);
    //查询问卷的类型
    Questionnaire findQuestionnaireType(String id);
    //查询使用中的问卷
    List<Questionnaire> getQuestionnaireByState();
    //查询所有的问卷
    List<Questionnaire> findQuestionnaireAll();
}