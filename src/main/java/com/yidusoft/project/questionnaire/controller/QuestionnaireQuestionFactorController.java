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

    @PostMapping
    public Result add(QuestionnaireQuestionFactor questionnaireQuestionFactor) {
        questionnaireQuestionFactorService.save(questionnaireQuestionFactor);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        questionnaireQuestionFactorService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(QuestionnaireQuestionFactor questionnaireQuestionFactor) {
        questionnaireQuestionFactorService.update(questionnaireQuestionFactor);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        QuestionnaireQuestionFactor questionnaireQuestionFactor = questionnaireQuestionFactorService.findById(id);
        return ResultGenerator.genSuccessResult(questionnaireQuestionFactor);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionnaireQuestionFactor> list = questionnaireQuestionFactorService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
