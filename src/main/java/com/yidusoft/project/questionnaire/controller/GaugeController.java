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

    /**
     * 添加量表
     * @param gauge
     * @param questionStr
     * @return
     */
    @PostMapping("/addGauge")
    @ResponseBody
    public Result addGauge(Gauge gauge, String questionStr,String tagId,String sceneId) {
        try {
            gaugeService.addGauge(gauge,questionStr,tagId,sceneId);
        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 修改量表
     * @param gauge
     * @param questionStr
     * @return
     */
    @PostMapping("/updateGauge")
    @ResponseBody
    public Result updateGauge(Gauge gauge, String questionStr) {
        try {
            gaugeService.updateGauge(gauge,questionStr);
        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }
        return ResultGenerator.genSuccessResult();
    }

    /***
     * 逻辑删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteGauge")
    @ResponseBody
    public Result deleteGauge(String ids) {
        try {
            String arr[] = ids.split(",");
            for (String str : arr) {
                Gauge gauge = gaugeService.findById(str);
                gauge.setDeleted(1);
                gaugeService.update(gauge);
            }

        } catch (Exception e) {
            return ResultGenerator.genFailResult("删除失败！");
        }

        return ResultGenerator.genSuccessResult();
    }

    /**
     * 查询所有的量表
     * @return
     */
    @PostMapping("/findGaugeAll")
    @ResponseBody
    public Result findGaugeAll() {
       List<Gauge> gaugeList= gaugeService.findGaugeAll();
        return ResultGenerator.genSuccessResult(gaugeList);
    }

    //查询量表相关的 信息 标签  场景
    @PostMapping("/getGaugeInfo")
    @ResponseBody
    public Result getGaugeInfo(String gaugeId) {

        return gaugeService.getGaugeInfo(gaugeId);
    }

}
