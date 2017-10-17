package com.yidusoft.project.questionnaire.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yhdj on 2017/10/16.
 */

@Controller
@RequestMapping(value = "/web/questionnaire/questionnaireTag")
public class QuestionnaireTagDetail {

    @RequestMapping(value = "/questionnaireTagDetail")
    public String questionnaireTagDetail(){
        return "project/cube/questionnaire/questionnaireTag/questionnaireTagList";
    }
}
