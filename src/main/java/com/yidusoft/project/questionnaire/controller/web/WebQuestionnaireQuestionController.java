package com.yidusoft.project.questionnaire.controller.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by zcb on 2017/10/12.
 */
@Controller
@RequestMapping("/web/questionnaire/question")
public class WebQuestionnaireQuestionController {

    /**
     * 跳转到问卷问题的页面
     * @return
     */
    @RequestMapping("/getQuestionnaireQuestion")
    public String getQuestionnaireQuestion() {
        return "project/questionnaire/question/questionnaire-question";
    }

    /**
     * 跳转到问卷问题的页面
     * @returng
     */
    @RequestMapping("/getGaugeQuestion")
    public String getQuestion() {
        return "project/questionnaire/question/gauge-question";
    }

    /**
     * 跳转到问题添加
     * @returng
     */
    @RequestMapping("/getAddQuestion")
    public String getAddQuestion(String ascriptionType, String  ids, String  flg, Model model) {
        model.addAttribute("ascriptionType",ascriptionType);
        model.addAttribute("ids",ids);
        model.addAttribute("flg",flg);
        return "project/questionnaire/question/save-question";
    }
}
