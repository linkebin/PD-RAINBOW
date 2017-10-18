package com.yidusoft.project.questionnaire.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yhdj on 2017/10/12.
 * 用来做与跳转到问卷分类界面
 */
@Controller
@RequestMapping("/web/questionnaire/question")
public class WebQuestionnaireTypeController {

    @RequestMapping(value = "/questionnaireTypeDetail")
    public String questionnaireTypeDetail(){
        return "project/questionnaire/questionType/questionnaireTypeList";
    }
}
