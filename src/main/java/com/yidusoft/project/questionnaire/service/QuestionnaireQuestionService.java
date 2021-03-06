package com.yidusoft.project.questionnaire.service;
import com.yidusoft.core.Result;
import com.yidusoft.core.Service;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnaireQuestionService extends Service<QuestionnaireQuestion> {
    //分页条件查询问题
   List<QuestionnaireQuestion> questionListByPage(QuestionnaireQuestion questionnaireQuestion);
    //问卷或量表  添加问题 查询没有添加的问题
    List<QuestionnaireQuestion> findQuestionBYid(QuestionnaireQuestion questionnaireQuestion);
    //查询问卷相关的问题
    List<QuestionnaireQuestion> findQuestionForQuestionnaire(String id);
    //提交问卷
    Result  submitQuestionnaire(String param, String questionnaireId,String userId,String visitorTimes,String timeConsuming,String activityId,String userName);
    //活动提交问卷
    Result subQuestionnaire(String param, String questionnaireId, String userId, String visitorTimes, String timeConsuming, String activityId, String userName);

}
