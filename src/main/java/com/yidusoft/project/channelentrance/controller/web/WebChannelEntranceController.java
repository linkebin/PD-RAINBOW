package com.yidusoft.project.channelentrance.controller.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.utils.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smy on 2017/11/8.
 */
@Controller
@RequestMapping(value = "/web/channelentrance")
public class WebChannelEntranceController {

    @Autowired
    private ChannelManageService channelManageService;

    @RequestMapping(value = "/accountmanage")
    public String accountManage() {
       return "project/channelentrance/account-manage";
    }

    @RequestMapping(value = "/consultManage")
    public String consultManage() {
        return "project/channelentrance/consult-manage";
    }

    @RequestMapping(value = "/historyClearLog")
    public String historyClearLog(Model model) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("channel_id", Security.getUser().getChannelId());
        List<Map<String,Object>> list = channelManageService.findChannelAccountListByChannelId(map);
        model.addAttribute("channelAccountMap",list);

        return "project/channelentrance/history-clear-log";
    }

}
