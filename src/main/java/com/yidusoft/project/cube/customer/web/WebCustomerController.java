package com.yidusoft.project.cube.customer.web;

import com.yidusoft.project.system.domain.SelectOption;
import com.yidusoft.project.system.service.SelectOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by smy on 2017/10/19.
 */
@Controller
@RequestMapping("/web/customer")
public class WebCustomerController {
    @Autowired
    private SelectOptionService selectOptionService;

    @RequestMapping("/customerList")
    public String customerList(){

        return "project/cube/customer/customer-list";
    }

    @RequestMapping("/visitorRegister")
    public String visitorRegister(Model model,String creator){

        model.addAttribute("creator",creator);
        List<SelectOption> selectOptionList = selectOptionService.findSelectOptionByType("profession");
        model.addAttribute("selectOptionList",selectOptionList);

        return "project/cube/customer/visitor-register";
    }

    @RequestMapping("/visitorUpdate")
    public String visitorUpdate(Model model,String id){

        model.addAttribute("id",id);
        List<SelectOption> selectOptionList = selectOptionService.findSelectOptionByType("profession");
        model.addAttribute("selectOptionList",selectOptionList);

        return "project/cube/customer/visitor-update";
    }
}
