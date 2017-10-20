package com.yidusoft.project.questionnaire.service;
import com.yidusoft.core.Result;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTagMiddle;
import com.yidusoft.core.Service;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnaireTagMiddleService extends Service<QuestionnaireTagMiddle> {

    //绑定标签与问卷
    Result addQuestionnaireTagMiddle(String tagId, String questionnaireId);
    //删除绑定的标签问卷
    Result  deleteQuestionnaireTagMiddle(QuestionnaireTagMiddle questionnaireTagMiddle);
}
