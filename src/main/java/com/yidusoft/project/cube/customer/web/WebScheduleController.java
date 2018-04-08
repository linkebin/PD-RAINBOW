package com.yidusoft.project.cube.customer.web;

import com.yidusoft.project.business.service.ScheduleService;
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
@RequestMapping("/web/schedule")
public class WebScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/visitor/schedule")
    public String customerList(String type,Model model,String id){

        model.addAttribute("type",type);
        model.addAttribute("id",id);
        model.addAttribute("schedule",scheduleService.findById(id));

        return "project/cube/customer/visitor-schedule";
    }

}
