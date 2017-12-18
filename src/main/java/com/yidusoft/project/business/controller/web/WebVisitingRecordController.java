package com.yidusoft.project.business.controller.web;

import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.domain.VisitorRegister;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import com.yidusoft.project.business.service.VisitorRegisterService;
import com.yidusoft.project.system.domain.SelectOption;
import com.yidusoft.project.system.service.SelectOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by L on 2017/10/31.
 */
@Controller
@RequestMapping("/web/visiting/record")
public class WebVisitingRecordController {

    @Resource
    private LaunchActivitiesService launchActivitiesService;
    @Resource
    private VisitorRegisterService visitorRegisterService;

    @Autowired
    private SelectOptionService selectOptionService;

    @RequestMapping("/goal")
    public String acdetail() {
        return "project/system/visitor-purpose";
    }

    @RequestMapping("/consultantUpdate")
    public String consultantUpdate(String id,Model model) {
        model.addAttribute("id",id);
        VisitorRegister visitorRegister = visitorRegisterService.findById(id);
        model.addAttribute("visitorRegister",visitorRegister);
        List<SelectOption> selectOptionList =selectOptionService.findSelectOptionByType("goal");
        model.addAttribute("selectOptionList",selectOptionList);
        return "project/cube/customer/consultant-update";
    }
}
