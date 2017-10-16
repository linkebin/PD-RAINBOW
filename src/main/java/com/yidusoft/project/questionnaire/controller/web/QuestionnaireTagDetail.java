package com.yidusoft.project.questionnaire.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by yhdj on 2017/10/16.
 */

@Controller
public class QuestionnaireTagDetail {

    @GetMapping(value = "/questionnaireTagDetail")
    public String questionnaireTagDetail(){
        return "project/cube/questionnaire/questionnaireTag/questionnaireTagList";
    }
}
