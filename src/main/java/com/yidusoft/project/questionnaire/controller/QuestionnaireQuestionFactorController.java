package com.yidusoft.project.questionnaire.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestionFactor;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionFactorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/questionnaire/question/factor")
public class QuestionnaireQuestionFactorController {
    @Resource
    private QuestionnaireQuestionFactorService questionnaireQuestionFactorService;




}
