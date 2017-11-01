package com.yidusoft.project.cube.business.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by L on 2017/10/30.
 */
@Controller
@RequestMapping(value = "/web/activities")
public class WebCubeActivitiesController {

    @RequestMapping(value = "/activitiesList")
    public String promotionsList() {
        return "project/cube/business/activities-list";
    }

    @RequestMapping(value = "/initiatingActivities")
    public String activities() {
        return "project/cube/business/initiating-activities";
    }

    @RequestMapping(value="/activitiesUpdate")
    public String update(Model model,String id){
        model.addAttribute("id",id);
        return "project/cube/business/activities-update";
    }

    @RequestMapping("/fillingPage")
    public String fillig(){
        return "project/cube/business/filling-page";
    }
}
