package com.yidusoft.project.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.Gauge;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.GaugeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/gauge")
public class GaugeController {
    @Resource
    private GaugeService gaugeService;


    /**
     * 分页条件查询所有的量表
     * @param params
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/gaugeListByPage")
    @ResponseBody
    public Result augeListByPage(String params, int page, int size) {
        Gauge gauge= JSON.parseObject(params,Gauge.class);
        PageHelper.startPage(page, size);
        List<Gauge> list =gaugeService.gaugeListByPage(gauge);
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


     /*------------------下面系统自动生成            分割--------------------------------*/

    @PostMapping
    public Result add(Gauge gauge) {
        gaugeService.save(gauge);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        gaugeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(Gauge gauge) {
        gaugeService.update(gauge);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        Gauge gauge = gaugeService.findById(id);
        return ResultGenerator.genSuccessResult(gauge);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Gauge> list = gaugeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
