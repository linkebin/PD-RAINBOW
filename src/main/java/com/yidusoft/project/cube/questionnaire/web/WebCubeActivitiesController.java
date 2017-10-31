package com.yidusoft.project.cube.questionnaire.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by L on 2017/10/30.
 */
@Controller
@RequestMapping(value = "/web/activities")
public class WebCubeActivitiesController {

    @RequestMapping(value = "/activitiesList")
    public String promotionsList() {
        return "project/cube/activities/activities-list";
    }

    @RequestMapping(value = "/initiatingActivities")
    public String activities() {
        return "project/cube/activities/initiating-activities";
    }
}
