package com.yidusoft.project.business.controller.web;

import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by L on 2017/10/31.
 */
@Controller
@RequestMapping("/web/launchActivities")
public class WebLaunchActivitiesController {

    @Resource
    private LaunchActivitiesService launchActivitiesService;

    @RequestMapping("/linkList")
    public String linkList() {
        return "project/business/launchActivities/launchActivitiesList";
    }

    @RequestMapping("/list")
    public String list() {
        return "project/business/launchActivities/launchActivityList";
    }

    @RequestMapping("/add")
    public String add(){ return "project/business/launchActivities/launchActivityAdd"; }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable String id, Model model){
        LaunchActivities launchActivities = launchActivitiesService.findById(id);
        model.addAttribute("activity",launchActivities);
        return "project/business/launchActivities/launchActivityUpdate";
    }

    @RequestMapping("/check/{id}")
    public String check(@PathVariable String id, Model model){
        LaunchActivities launchActivities = launchActivitiesService.findById(id);
        model.addAttribute("activity",launchActivities);
        return "project/business/launchActivities/launchActivityCheck";
    }

    @RequestMapping("/ActivityCheck/{id}")
    public String ActivityCheck(@PathVariable String id, Model model){
        LaunchActivities launchActivities = launchActivitiesService.findById(id);
        model.addAttribute("activity",launchActivities);
        return "project/business/launchActivities/activityCheck";
    }
}
