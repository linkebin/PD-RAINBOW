package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTag;

import java.util.List;

public interface QuestionnaireTagMapper extends Mapper<QuestionnaireTag> {
    List<QuestionnaireTag> questionnaireTagListByPage(QuestionnaireTag questionnaireTag);
   //查询量表相关的标签
   List<QuestionnaireTag>  findTagForGauge(String gaugeId);
    //查询问卷相关的标签
    List<QuestionnaireTag>   findTagForQuestionnaire(String questionnaireId);
}