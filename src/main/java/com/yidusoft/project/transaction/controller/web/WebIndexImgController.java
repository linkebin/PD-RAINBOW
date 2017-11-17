package com.yidusoft.project.transaction.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by L on 2017/10/24.
 */
@Controller
@RequestMapping("/web/indexImg")
public class WebIndexImgController {
    @RequestMapping("/linkList")
    public String linkList() {
        return "project/transaction/indexImg/indexImgList";
    }

    @RequestMapping("/imgAdd")
    public String imgAdd() {
        return "project/transaction/indexImg/indexImgAdd";
    }

    @RequestMapping("/imgUpdate/{id}")
    public String imgUpdate(@PathVariable String id, Model model) {
        model.addAttribute("id",id);
        return "project/transaction/indexImg/indexImgUpdate";
    }
}
