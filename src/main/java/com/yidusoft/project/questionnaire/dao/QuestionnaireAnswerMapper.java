package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionnaireAnswerMapper extends Mapper<QuestionnaireAnswer> {
    List<QuestionnaireQuestion> questionList(String questionnaireId);

    //查询来访者填写问卷的答案 相关
    List<QuestionnaireAnswer>  findAnswerForAcquisition(QuestionnaireAnswer questionnaireAnswer);

    /**
     *  查询活动的所有填写答案
     *  @param activityId
     */
    List<QuestionnaireAnswer> findAnswerForActivity(@Param(value = "activityId") String activityId);
}