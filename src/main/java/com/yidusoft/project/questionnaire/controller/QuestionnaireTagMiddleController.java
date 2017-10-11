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

    @PostMapping
    public Result add(QuestionnaireTagMiddle questionnaireTagMiddle) {
        questionnaireTagMiddleService.save(questionnaireTagMiddle);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        questionnaireTagMiddleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(QuestionnaireTagMiddle questionnaireTagMiddle) {
        questionnaireTagMiddleService.update(questionnaireTagMiddle);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        QuestionnaireTagMiddle questionnaireTagMiddle = questionnaireTagMiddleService.findById(id);
        return ResultGenerator.genSuccessResult(questionnaireTagMiddle);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionnaireTagMiddle> list = questionnaireTagMiddleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
