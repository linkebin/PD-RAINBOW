package com.yidusoft.project.questionnaire.controller.web;

import com.yidusoft.project.questionnaire.domain.Gauge;
import com.yidusoft.project.questionnaire.domain.GaugeQuestionFactor;
import com.yidusoft.project.questionnaire.service.GaugeQuestionFactorService;
import com.yidusoft.project.questionnaire.service.GaugeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zcb on 2017/10/13.
 */
@Controller
@RequestMapping("/web/gauge")
public class WebGaugeController {

    @Resource
    private GaugeService gaugeService;
    @Resource
    private GaugeQuestionFactorService gaugeQuestionFactorService;
    //跳转量表查询列表页面
    @RequestMapping("/getGauge")
    public  String  getGauge(){
        return  "project/questionnaire/gauge/gauge";
    }

    //跳转量表添加页面
    @RequestMapping("/getAddGauge")
    public  String  getAddGauge(){
        return  "project/questionnaire/gauge/add-gauge";
    }
    //跳转量表修改页面
    @RequestMapping("/getUpdateGauge")
    public  String  getUpdateGauge(String id, Model model){
        //查询量表信息
        Gauge gauge=gaugeService.findById(id);
        //查询量表相关的问题
        List<GaugeQuestionFactor> list=gaugeQuestionFactorService.findGaugeQuestionFactor(gauge.getId());
        model.addAttribute("questionList",list);
        model.addAttribute("gauge",gauge);
        return  "project/questionnaire/gauge/update-gauge";
    }


}



