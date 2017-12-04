package com.yidusoft.project.channel.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.AccountRule;
import com.yidusoft.project.channel.service.AccountRuleService;
import com.yidusoft.project.channel.service.ChannelRuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@Controller
@RequestMapping("/web/account/rule")
public class WebAccountRuleController {
    @Resource
    private AccountRuleService accountRuleService;

    @Resource
    private ChannelRuleService channelRuleService;

    @RequestMapping("/openlist")
    public String openlist() {

        return "project/channel/rule-list";
    }


    @RequestMapping("/timeline")
    public String timeline(String channelId, Model model) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("channel_id",channelId);
        model.addAttribute("channelRuleMaps",channelRuleService.findRuleByChannel(map));
        return "project/channel/channel-rule-timeline";
    }

    @RequestMapping("/clearing-new")
    public String clearingNew() {
        return "project/channel/channel-clearing-new";
    }

    @RequestMapping("/clearing-manage")
    public String clearingManage() {
        return "project/channel/clearing-manage";
    }
}
