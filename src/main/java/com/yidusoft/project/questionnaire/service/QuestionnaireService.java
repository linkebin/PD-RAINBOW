package com.yidusoft.project.questionnaire.service;
import com.yidusoft.core.Result;
import com.yidusoft.project.questionnaire.domain.Questionnaire;
import com.yidusoft.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnaireService extends Service<Questionnaire> {
    //分页条件查询相关的问卷信息
    List<Questionnaire> questionnaireListByPage(Questionnaire questionnaire);
    //添加问卷
    Result addQuestionnaire(Questionnaire questionnaire, String questionStr,String tagId,String sceneId, String userIds);

     //查询问卷 相关的 标签   场景
    Result  getQuestionnaireInfo(String id);
    //修改问卷信息
    Result updateQuestionnaire(Questionnaire questionnaire, String questionStr);
    //查询问卷即将要上架的 修改状态
    Result setQuestionnaireState();
    //查询使用中的问卷
    List<Questionnaire> getQuestionnaireByState();
    //选择填写的问卷    场景 标签 条件查询
    List<Questionnaire> findQuestionnaireForTagAndScene(Map<String,Object> map);
}
