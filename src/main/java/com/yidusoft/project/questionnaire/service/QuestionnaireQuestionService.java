package com.yidusoft.project.questionnaire.service;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnaireQuestionService extends Service<QuestionnaireQuestion> {
    //分页条件查询问题
   List<QuestionnaireQuestion> questionListByPage(QuestionnaireQuestion questionnaireQuestion);
}
