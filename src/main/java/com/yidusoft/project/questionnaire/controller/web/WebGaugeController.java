package com.yidusoft.project.questionnaire.controller.web;

import com.yidusoft.project.questionnaire.domain.Gauge;
import com.yidusoft.project.questionnaire.domain.GaugeQuestionFactor;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTag;
import com.yidusoft.project.questionnaire.domain.Scene;
import com.yidusoft.project.questionnaire.service.GaugeQuestionFactorService;
import com.yidusoft.project.questionnaire.service.GaugeService;
import com.yidusoft.project.questionnaire.service.QuestionnaireTagService;
import com.yidusoft.project.questionnaire.service.SceneService;
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
    @Resource
    private SceneService sceneService;
    @Resource
    private QuestionnaireTagService questionnaireTagService;

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
       //查询量表相关的场景
        List<Scene> sceneList=sceneService.findSceneForGauge(gauge.getId());
        //查询量表相关的场景
        List<QuestionnaireTag> questionnaireTagList=questionnaireTagService.findTagForGauge(gauge.getId());
        model.addAttribute("questionList",list);
        model.addAttribute("gauge",gauge);
        model.addAttribute("sceneList",sceneList);
        model.addAttribute("questionnaireTagList",questionnaireTagList);

        return  "project/questionnaire/gauge/update-gauge";
    }

    //跳转量表添加页面
    @RequestMapping("/getGaugeTag")
    public  String  getGaugeTag(){
        return  "project/questionnaire/gauge/gauge-tag";
    }

    //跳转量表添加页面
    @RequestMapping("/getGaugeScene")
    public  String  getGaugeScene(){
        return  "project/questionnaire/gauge/gauge-scene";
    }



}



