package com.yidusoft.project.questionnaire.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTagMiddle;
import com.yidusoft.project.questionnaire.service.QuestionnaireTagMiddleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/questionnaire/tag/middle")
public class QuestionnaireTagMiddleController {
    @Resource
    private QuestionnaireTagMiddleService questionnaireTagMiddleService;


    /**
     * 添加标签
     * @param ids
     * @param questionnaireId
     * @return
     */
    @PostMapping("/addQuestionnaireTagMiddle")
    public Result addQuestionnaireTagMiddle(String ids,String questionnaireId) {

        return questionnaireTagMiddleService.addQuestionnaireTagMiddle(ids,questionnaireId);
    }

    /**
     *  删除绑定的标签问卷
     * @param ids
     * @param questionnaireId
     * @return
     */
    @PostMapping("/deleteQuestionnaireTagMiddle")
    public  Result  deleteQuestionnaireTagMiddle(String ids,String questionnaireId){

        QuestionnaireTagMiddle questionnaireTagMiddle =new QuestionnaireTagMiddle();
        questionnaireTagMiddle.setQuestionnaireId(questionnaireId);
        questionnaireTagMiddle.setQuestionnaireTagId(ids);
        return  questionnaireTagMiddleService.deleteQuestionnaireTagMiddle(questionnaireTagMiddle);
    }



}
