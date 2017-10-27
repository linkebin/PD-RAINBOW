package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;

import java.util.List;

public interface QuestionnaireAnswerMapper extends Mapper<QuestionnaireAnswer> {
    List<QuestionnaireQuestion> questionList(String questionnaireId);

    //查询来访者填写问卷的答案 相关
    List<QuestionnaireAnswer>  findAnswerForAcquisition(QuestionnaireAnswer questionnaireAnswer);


}