package com.yidusoft.project.channel.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@Controller
@RequestMapping("/web/channel/manage")
public class WebChannelManageController {
    @Resource
    private ChannelManageService channelManageService;

    @RequestMapping("/openlist")
    public String openlist() {

        return "project/channel/channel-list";
    }

    @RequestMapping("/openaccounts")
    public String openaccounts(String id, Model model) {
        model.addAttribute("channelId",id);
        return "project/channel/channel-accounts";
    }

}
