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

    @RequestMapping("/acdetail")
    public String acdetail(String id,Model model) {
        LaunchActivities launchActivities = launchActivitiesService.findById(id);
        model.addAttribute("acdetail",launchActivities);
        return "project/business/launchActivities/acdetail";
    }
    //跳转到调研活动管理
    @RequestMapping("/linkList")
    public String linkList() {
        return "project/business/launchActivities/launchActivitiesList";
    }

    //跳转到企业活动管理
    @RequestMapping("/list")
    public String list() {
        return "project/business/launchActivities/launchActivityList";
    }

    //跳转到公益活动
    @RequestMapping("/commonwealList")
    public String commonwealList() {
        return "project/business/launchActivities/commonwealActiveList";
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
