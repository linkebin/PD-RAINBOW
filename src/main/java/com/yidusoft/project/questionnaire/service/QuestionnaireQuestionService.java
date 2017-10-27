package com.yidusoft.project.questionnaire.service;
import com.yidusoft.core.Result;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnaireQuestionService extends Service<QuestionnaireQuestion> {
    //分页条件查询问题
   List<QuestionnaireQuestion> questionListByPage(QuestionnaireQuestion questionnaireQuestion);
    //问卷或量表  添加问题 查询没有添加的问题
    List<QuestionnaireQuestion> findQuestionBYid(String ids);
    //查询问卷相关的问题
    List<QuestionnaireQuestion> findQuestionForQuestionnaire(String id);
    //提交问卷
    Result  submitQuestionnaire(String param, String questionnaireId,String userId,String visitorTimes);

}
