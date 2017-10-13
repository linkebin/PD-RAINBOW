package com.yidusoft.project.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.system.domain.SecRole;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/questionnaire/question")
public class QuestionnaireQuestionController {
    @Resource
    private QuestionnaireQuestionService questionnaireQuestionService;



    /**
     * 分页条件查询所有的问题
     * @param params
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/questionListByPage")
    @ResponseBody
    public Result questionListByPage(String params, int page, int size) {
        QuestionnaireQuestion questionnaireQuestion=JSON.parseObject(params,QuestionnaireQuestion.class);
        PageHelper.startPage(page, size);
        List<QuestionnaireQuestion> list =questionnaireQuestionService.questionListByPage(questionnaireQuestion);
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/addORupdateQuestion")
    @ResponseBody
    public Result addORupdateQuestion(QuestionnaireQuestion questionnaireQuestion) {
        //修改删除


        return ResultGenerator.genSuccessResult("");
    }



    /*------------------下面系统自动生成            分割--------------------------------*/


    @PostMapping
    public Result add(QuestionnaireQuestion questionnaireQuestion) {
        questionnaireQuestionService.save(questionnaireQuestion);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        questionnaireQuestionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(QuestionnaireQuestion questionnaireQuestion) {
        questionnaireQuestionService.update(questionnaireQuestion);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        QuestionnaireQuestion questionnaireQuestion = questionnaireQuestionService.findById(id);
        return ResultGenerator.genSuccessResult(questionnaireQuestion);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionnaireQuestion> list = questionnaireQuestionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
