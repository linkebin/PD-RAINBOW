package com.yidusoft.project.cube.user.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by smy on 2017/10/18.
 */
@Controller
@RequestMapping("/web/user")
public class WebUserController {

    @RequestMapping("/userInfo")
    public String userInfo(){
        return "project/cube/user/user-info";
    }

    @RequestMapping("/userInfoA")
    public String userInfoA(){
        return "project/channelentrance/user-info";
    }

    /**
     * 跳转到账户信息
     * @return
     */
    @RequestMapping("/account")
    public String accountInfo(){
        return "project/cube/user/account-info";
    }
}
