package com.yidusoft.project.transaction.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.QuestionnairePromotions;
import com.yidusoft.project.transaction.service.QuestionnairePromotionsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/questionnaire/promotions")
public class QuestionnairePromotionsController {
    @Resource
    private QuestionnairePromotionsService questionnairePromotionsService;

    @PostMapping
    public Result add(QuestionnairePromotions questionnairePromotions) {
        questionnairePromotionsService.save(questionnairePromotions);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        questionnairePromotionsService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(QuestionnairePromotions questionnairePromotions) {
        questionnairePromotionsService.update(questionnairePromotions);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        QuestionnairePromotions questionnairePromotions = questionnairePromotionsService.findById(id);
        return ResultGenerator.genSuccessResult(questionnairePromotions);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionnairePromotions> list = questionnairePromotionsService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
