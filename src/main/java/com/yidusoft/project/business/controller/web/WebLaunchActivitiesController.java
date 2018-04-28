package com.yidusoft.project.business.controller.web;

import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import com.yidusoft.project.questionnaire.domain.Questionnaire;
import com.yidusoft.project.questionnaire.service.QuestionnaireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static com.yidusoft.configurer.ResourcesStatic.GAUGE;

/**
 * Created by L on 2017/10/31.
 */
@Controller
@RequestMapping("/web/launchActivities")
public class WebLaunchActivitiesController {

    @Resource
    private LaunchActivitiesService launchActivitiesService;
    @Resource
    private QuestionnaireService  questionnaireService;

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

    /**
     * 跳转到活动管理监控页面
     * @param activitiesId
     * @param model
     * @return
     */
    @RequestMapping("/presentation/{activitiesId}")
    public String presentation(@PathVariable String  activitiesId,Model model) {
        model.addAttribute("activitiesId",activitiesId);
        LaunchActivities launchActivities=  launchActivitiesService.findById(activitiesId);
        //获取量表名称，通过量表名称得到对应的页面
        Questionnaire questionnaire = questionnaireService.findQuestionnaireType(launchActivities.getUestionnaireId());
        String htmlName="activity_presentation";
        if(questionnaire!=null){
            htmlName=(GAUGE.get(questionnaire.getGaugeName())==null?"activity_presentation":htmlName+"_"+GAUGE.get(questionnaire.getGaugeName()).toString());
        }
        return "project/business/launchActivities/statistics/"+htmlName;
    }

    /**
     * 活动管理页面
     * @return
     */
    @RequestMapping("/getLaunchActivityAll")
    public String getLaunchActivityAll() {

        return "project/business/launchActivities/launchActivityManagement";
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
