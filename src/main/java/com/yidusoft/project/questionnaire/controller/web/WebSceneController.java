package com.yidusoft.project.questionnaire.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yhdj on 2017/10/17.
 * 跳转到问卷场景
 */

@Controller
@RequestMapping(value = "/web/questionnaire/scene")
public class WebSceneController {

    @RequestMapping(value = "/getScene")
    public String getQuestionnaireScene(){
        return "project/questionnaire/scene/scene";
    }
}
