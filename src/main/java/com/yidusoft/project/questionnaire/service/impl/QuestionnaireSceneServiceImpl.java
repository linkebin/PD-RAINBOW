package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.dao.QuestionnaireSceneMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireScene;
import com.yidusoft.project.questionnaire.service.QuestionnaireSceneService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireSceneServiceImpl extends AbstractService<QuestionnaireScene> implements QuestionnaireSceneService {
    @Resource
    private QuestionnaireSceneMapper questionnaireSceneMapper;


    //绑定场景与问卷
    @Override
    public Result addQuestionnaireScene(String sceneId, String questionnaireId) {
        try{
            String [] sceneArray=sceneId.split(",");
            for(int i=0; i<sceneArray.length;i++){
                if(!"".equals(sceneArray[i])){
                QuestionnaireScene questionnaireScene=new QuestionnaireScene();
                questionnaireScene.setId(UUID.randomUUID().toString());
                questionnaireScene.setQuestionnaireId(questionnaireId);
                questionnaireScene.setSceneId(sceneArray[i]);
                questionnaireSceneMapper.insert(questionnaireScene);
            }
            }
        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }

        return ResultGenerator.genSuccessResult();
    }
    //删除问卷相关的场景
    @Override
    public Result deleteQuestionnaireScene(QuestionnaireScene questionnaireScene) {
        try {
            QuestionnaireScene questionnaireScene1= questionnaireSceneMapper.deleteQuestionnaireScene(questionnaireScene);
            questionnaireSceneMapper.delete(questionnaireScene1);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
        return ResultGenerator.genSuccessResult();
    }
}
