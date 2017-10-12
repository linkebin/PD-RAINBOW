package com.yidusoft.project.questionnaire.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by zcb on 2017/10/12.
 */
@Controller
@RequestMapping("/web/questionnaire/question")
public class WebQuestionnaireQuestionController {

    /**
     * 跳转到问题的页面
     * @return
     */
    @RequestMapping("/getQuestion")
    public String getQuestion() {
        return "project/questionnaire/question/question";
    }

}
