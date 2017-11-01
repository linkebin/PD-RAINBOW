package com.yidusoft.project.business.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by L on 2017/10/31.
 */
@Controller
@RequestMapping("/web/launchActivities")
public class WebLaunchActivitiesController {

    @RequestMapping("/linkList")
    public String linkList() {
        return "project/business/launchActivities/launchActivitiesList";
    }

    @RequestMapping("/approval")
    public String add(){
        return "project/business/launchActivities/activityApproval";
    }
}
