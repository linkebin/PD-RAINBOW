package com.yidusoft.project.system.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by linkb on 2017/8/22.
 */
@Controller
@RequestMapping("web/msg")
public class WebMsgController {
    @RequestMapping("/msgCentre")
    public String msgCentre(){
        return "TaskMsg";
    }

}
