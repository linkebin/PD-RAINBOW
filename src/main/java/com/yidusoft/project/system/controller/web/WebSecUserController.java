package com.yidusoft.project.system.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zcs on 2017/8/9.
 */
@Controller
@RequestMapping("web/sec/user")
public class WebSecUserController {

    @RequestMapping("/linkList")
    public String list(){
        return "project/system/secuser/SecuserList";
    }

    @RequestMapping(value ="/linkAdd")
    public String linkAdd(){
        return  "project/system/secuser/SecuserAdd";
    }

    @RequestMapping("/info")
    public String info(){
        return "project/system/secuser/SecuserInfo";
    }

    @RequestMapping("/userRole")
    public String userRole(String id, Model model){
        model.addAttribute("id",id);
        return "project/system/secuser/user-roles";
    }

    @RequestMapping("/linkUserAddRole")
    public String linkUserAddRole(String id, Model model){
        model.addAttribute("id",id);
        return "project/system/secuser/user-add-role";
    }



}
