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
@RequestMapping("/web/visiting/record")
public class WebVisitingRecordController {

    @Resource
    private LaunchActivitiesService launchActivitiesService;

    @RequestMapping("/goal")
    public String acdetail() {
        return "project/system/visitor-purpose";
    }
}
