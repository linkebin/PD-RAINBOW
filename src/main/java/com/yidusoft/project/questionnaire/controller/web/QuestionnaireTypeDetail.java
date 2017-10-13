package com.yidusoft.project.questionnaire.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by yhdj on 2017/10/12.
 * 用来做与跳转到问卷分类界面
 */
@Controller
public class QuestionnaireTypeDetail {

    @GetMapping(value = "/questionnaireTypeDetail")
    public String questionnaireTypeDetail(){
        return "project/cube/questionnaire/questionType/questionnaireTypeList";
    }
}
