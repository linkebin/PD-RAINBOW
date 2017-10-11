package com.yidusoft.project.system.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zcb on 2017/8/30.
 */
@Controller
@RequestMapping("web/menu")
public class WebSecMenuController {
    @RequestMapping("/getSecMenuList")
    public  String getSecMenuList(){

        return "project/system/secmenu/SecMenuList";
    }


}
