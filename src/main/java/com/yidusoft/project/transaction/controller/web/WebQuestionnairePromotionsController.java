package com.yidusoft.project.transaction.controller.web;

import com.yidusoft.project.transaction.domain.QuestionnairePromotions;
import com.yidusoft.project.transaction.service.QuestionnairePromotionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by L on 2017/10/13.
 */
@Controller
@RequestMapping("web/promotion")
public class WebQuestionnairePromotionsController {
    @Resource
    QuestionnairePromotionsService questionnairePromotionsService;

    @RequestMapping("/linkList")
    public String linkList() {
        return "project/transaction/questionnairePromotions/questionnairePromotionsList";
    }

    @RequestMapping("/add")
    public String add() {
        return "project/transaction/questionnairePromotions/questionnairePromotionsAdd";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model) {
        QuestionnairePromotions questionnairePromotions = questionnairePromotionsService.findById(id);
        model.addAttribute("promotions", questionnairePromotions);
        return "project/transaction/questionnairePromotions/questionnairePromotionsUpdate";
    }

    @RequestMapping("/choose/{type}")
    public String choose(@PathVariable String type,Model model) {
        model.addAttribute("type",type);
        return "project/transaction/questionnairePromotions/questionnairePromotionsChoose";
    }
}
