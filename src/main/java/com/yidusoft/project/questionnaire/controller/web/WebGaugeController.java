package com.yidusoft.project.questionnaire.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zcb on 2017/10/13.
 */
@Controller
@RequestMapping("/web/gauge")
public class WebGaugeController {

    //跳转量表查询列表页面
    @RequestMapping("/getGauge")
    public  String  getGauge(){
        return  "project/questionnaire/gauge/gauge";
    }

    @RequestMapping("/getd")
    public  String  getd(){
        return  "project/questionnaire/gauge/add-gauge";
    }

}



