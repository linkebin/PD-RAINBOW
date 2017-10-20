package com.yidusoft.project.questionnaire.service;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireScene;
import com.yidusoft.core.Service;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnaireSceneService extends Service<QuestionnaireScene> {
     //添加场景与问卷
     Result addQuestionnaireScene(String sceneId,String questionnaireId);
     //删除问卷相关的场景
     Result deleteQuestionnaireScene(QuestionnaireScene questionnaireScene);
}
