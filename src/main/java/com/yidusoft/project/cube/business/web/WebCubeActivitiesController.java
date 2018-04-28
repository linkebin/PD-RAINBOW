package com.yidusoft.project.cube.business.web;

import com.alibaba.fastjson.JSONObject;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


/**
 * Created by L on 2017/10/30.
 */
@Controller
@RequestMapping(value = "/web/activities")
public class WebCubeActivitiesController {

    /**
     * 跳转到企业活动列表页面
     * @return
     */
    @RequestMapping(value = "/activitiesList")
    public String promotionsList() {
        return "project/cube/business/activities-list";
    }

    /**
     * 跳转到发起活动页面
     * @param type
     * @param model
     * @return
     */
    @RequestMapping(value = "/initiatingActivities")
    public String activities(String type,Model model) {
        model.addAttribute("type",type);
        return "project/cube/business/initiating-activities";
    }

    /**
     * 跳转到活动修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="/activitiesUpdate")
    public String update(Model model,String id){
        model.addAttribute("id",id);
        return "project/cube/business/activities-update";
    }

    /**
     * 跳转到活动参与人填写信息页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/fillingPage")
    public String fillig(Model model,String id){
        model.addAttribute("id",id);
        return "project/cube/business/filling-page";
    }

    /**
     * 跳转到活动查看页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="/activitiesCheck")
    public String Check(Model model,String id){
        model.addAttribute("id",id);
        return "project/cube/business/activities-check";
    }

    /**
     *跳转到活动统计页面
     * @param model
     * @return
     */
    @RequestMapping(value="/getActivitiesStatistics")
    public String getActivitiesStatistics(Model model){
        model.addAttribute("userId", Security.getUserId());
        return "project/cube/business/activities_statistics";
    }
}
