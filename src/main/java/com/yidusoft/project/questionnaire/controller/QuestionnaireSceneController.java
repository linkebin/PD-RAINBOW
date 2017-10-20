package com.yidusoft.project.questionnaire.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireScene;
import com.yidusoft.project.questionnaire.service.QuestionnaireSceneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/questionnaire/scene")
public class QuestionnaireSceneController {
    @Resource
    private QuestionnaireSceneService questionnaireSceneService;


    /**
     *添加问卷相关联的场景
     * @param ids
     * @param questionnaireId
     * @return
     */
    @PostMapping("/addQuestionnaireScene")
    public Result addQuestionnaireScene(String ids,String questionnaireId) {
        return questionnaireSceneService.addQuestionnaireScene(ids,questionnaireId);
    }

    /***
     * 删除问卷相关的场景
     * @param
     * @return
     */
    @PostMapping("/deleteQuestionnaireScene")
    public Result  deleteQuestionnaireScene(String ids,String questionnaireId ) {
        QuestionnaireScene  questionnaireScene =new QuestionnaireScene();
        questionnaireScene.setSceneId(ids);
        questionnaireScene.setQuestionnaireId(questionnaireId);
     return    questionnaireSceneService.deleteQuestionnaireScene(questionnaireScene);
    }


}
