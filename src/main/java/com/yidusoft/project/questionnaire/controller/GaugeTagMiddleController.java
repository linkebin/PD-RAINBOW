package com.yidusoft.project.questionnaire.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.GaugeTagMiddle;
import com.yidusoft.project.questionnaire.service.GaugeTagMiddleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/gauge/tag/middle")
public class GaugeTagMiddleController {
    @Resource
    private GaugeTagMiddleService gaugeTagMiddleService;


    /***
     * 删除量表选择的场景
     * @param ids
     * @return
     */
    @PostMapping("deleteGaugeTagMiddle")
    public Result deleteGaugeTagMiddle(String  ids,String gaugeId) {
        GaugeTagMiddle gaugeTagMiddle=new GaugeTagMiddle();
        gaugeTagMiddle.setGaugeId(gaugeId);
        gaugeTagMiddle.setTagId(ids);
        return  gaugeTagMiddleService.deleteGaugeTagMiddle(gaugeTagMiddle);
    }

    /***
     * 添加
     * @param ids
     * @param gaugeId
     * @return
     */
    @PostMapping("addGaugeTagMiddle")
    public Result addGaugeTagMiddle(String  ids,String gaugeId) {

        return  gaugeTagMiddleService.addGaugeTagMiddle(ids, gaugeId);
    }


    @PostMapping
    public Result add(GaugeTagMiddle gaugeTagMiddle) {
        gaugeTagMiddleService.save(gaugeTagMiddle);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        gaugeTagMiddleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(GaugeTagMiddle gaugeTagMiddle) {
        gaugeTagMiddleService.update(gaugeTagMiddle);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        GaugeTagMiddle gaugeTagMiddle = gaugeTagMiddleService.findById(id);
        return ResultGenerator.genSuccessResult(gaugeTagMiddle);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<GaugeTagMiddle> list = gaugeTagMiddleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
