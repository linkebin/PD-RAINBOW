package com.yidusoft.project.transaction.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by L on 2017/10/13.
 */
@Controller
@RequestMapping("web/promotion")
public class WebQuestionnairePromotionsController {
    @RequestMapping("/linkList")
    public String linkList(){
        return "project/transaction/questionnairePromotions/questionnairePromotionsList";
    }
}
