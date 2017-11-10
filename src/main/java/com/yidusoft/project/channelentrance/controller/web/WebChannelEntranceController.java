package com.yidusoft.project.channelentrance.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by smy on 2017/11/8.
 */
@Controller
@RequestMapping(value = "/web/channelentrance")
public class WebChannelEntranceController {

    @RequestMapping(value = "/accountmanage")
    public String accountManage() {
       return "project/channelentrance/account-manage";
    }

    @RequestMapping(value = "/consultManage")
    public String consultManage() {
        return "project/channelentrance/consult-manage";
    }

}
