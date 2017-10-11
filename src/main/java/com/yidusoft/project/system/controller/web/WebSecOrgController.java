package com.yidusoft.project.system.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zcs on 2017/8/11.
 */

@Controller
@RequestMapping("/web/sec/org")
public class WebSecOrgController {

    @RequestMapping(value ="/linkList")
    public String linkList(){

        return  "project/system/secorg/SecorgList";
    }
    @RequestMapping(value ="/getSecorgLists")
    public String getSecorgLists(){

        return  "project/system/secorg/SecorgLists";
    }
}
