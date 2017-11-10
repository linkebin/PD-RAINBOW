package com.yidusoft.project.cube.user.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yhdj on 2017/11/7.
 */
@Controller
@RequestMapping("/web/user")
public class WebUserModifyPsdController {
    @RequestMapping("/modifyPassword")
    public String modifyPassword(){
        return "project/cube/user/modify-password";
    }

    @RequestMapping("/modifyPasswordA")
    public String modifyPasswordA(){
        return "project/channelentrance/modify-password";
    }
}
