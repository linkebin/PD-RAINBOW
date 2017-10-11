package com.yidusoft.project.questionnaire.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.GaugeQuestionFactor;
import com.yidusoft.project.questionnaire.service.GaugeQuestionFactorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/gauge/question/factor")
public class GaugeQuestionFactorController {
    @Resource
    private GaugeQuestionFactorService gaugeQuestionFactorService;

    @PostMapping
    public Result add(GaugeQuestionFactor gaugeQuestionFactor) {
        gaugeQuestionFactorService.save(gaugeQuestionFactor);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        gaugeQuestionFactorService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(GaugeQuestionFactor gaugeQuestionFactor) {
        gaugeQuestionFactorService.update(gaugeQuestionFactor);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        GaugeQuestionFactor gaugeQuestionFactor = gaugeQuestionFactorService.findById(id);
        return ResultGenerator.genSuccessResult(gaugeQuestionFactor);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<GaugeQuestionFactor> list = gaugeQuestionFactorService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
