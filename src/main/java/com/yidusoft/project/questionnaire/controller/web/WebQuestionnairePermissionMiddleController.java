package com.yidusoft.project.questionnaire.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yhdj on 2017/11/14.
 */
@Controller
@RequestMapping("/web/questionnairePermissionMiddle")
public class WebQuestionnairePermissionMiddleController {

    /**
     * 跳转到咨询师选择列表
     * @return
     */
    @RequestMapping("/findQuestionnaireSecUser")
    public String findQuestionnaireSecUser(){
        return "project/questionnaire/gauge/gauge-sec-user";
    }

    @RequestMapping("/showUserList")
    public String showUserList(){
        return "project/questionnaire/gauge/gauge-show-sec-user";
    }
}
