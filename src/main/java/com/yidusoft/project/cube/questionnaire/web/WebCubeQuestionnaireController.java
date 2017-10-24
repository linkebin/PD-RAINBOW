package com.yidusoft.project.cube.questionnaire.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zcb on 2017/10/23.
 */
@Controller
@RequestMapping(value ={"/web/cube/"})
public class WebCubeQuestionnaireController {

    /**
     * 跳转到问卷填写
     * @return
     */
    @RequestMapping(value ={"/getQuestionnaireFillIn"})
    public String getQuestionnaireFillIn(String id, Model model){
        model.addAttribute("questionnaireId",id);
        return "project/cube/questionnaire/vertical-questionnaire";
    }

}
