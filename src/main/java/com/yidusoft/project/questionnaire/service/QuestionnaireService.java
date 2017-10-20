package com.yidusoft.project.questionnaire.service;
import com.yidusoft.core.Result;
import com.yidusoft.project.questionnaire.domain.Questionnaire;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnaireService extends Service<Questionnaire> {
    //分页条件查询相关的问卷信息
    List<Questionnaire> questionnaireListByPage(Questionnaire questionnaire);
    //添加问卷
    Result addQuestionnaire(Questionnaire questionnaire, String questionStr,String tagId,String sceneId);

     //查询问卷 相关的 标签   场景
    Result  getQuestionnaireInfo(String id);
    //修改问卷信息
    Result updateQuestionnaire(Questionnaire questionnaire, String questionStr);
}
