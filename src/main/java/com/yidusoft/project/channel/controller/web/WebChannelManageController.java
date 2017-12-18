package com.yidusoft.project.channel.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@Controller
@RequestMapping("/web/channel/manage")
public class WebChannelManageController {
    @Resource
    private ChannelManageService channelManageService;

    @Autowired
    private SecUserService secUserService;

    @RequestMapping("/openlist")
    public String openlist() {

        return "project/channel/channel-list";
    }



    @RequestMapping("/channelPay")
    public String channelPay(String channel_id,Model model) {
        ChannelManage channelManage = channelManageService.findById(channel_id);
        model.addAttribute("channel_id",channelManage);

        return "project/channel/channel-pay";
    }

    @RequestMapping("/channelClearingStatistics")
    public String channelClearingStatistics() {

        return "project/channel/channel-clearing-statistics";
    }

    @RequestMapping("/onaylanmam")
    public String onaylanmamopenlist() {
        return "project/channel/onaylanmam-channel-list";
    }


    @RequestMapping("/openaccounts")
    public String openaccounts(String id, Model model) {
        model.addAttribute("channelId",id);
        return "project/channel/channel-accounts";
    }

    @RequestMapping("/counselor")
    public String counselor() {

        return "project/channel/channel-counselor";
    }

    @RequestMapping("/certificationCounselor")
    public String certificationCounselor(){
        return "project/channel/channel-counselor-certification";
    }

    @RequestMapping("/xxxx")
    public String xxxx() {
        return "project/channel/xxxx";
    }

    @RequestMapping("/acdetail")
    public String acdetail(String id,Model model) {

        model.addAttribute("acdetail",channelManageService.findById(id));

        return "project/channel/acdetail";
    }

    @RequestMapping("/counselorInfo")
    public String opencounselorInfo(String id,Model model) {
       SecUser user = secUserService.findById(id);
        model.addAttribute("user",user);
        return "project/channel/user-info";
    }

    @RequestMapping("/rulechannel")
    public String linkAdd(Model model) {
        model.addAttribute("UUID", UUID.randomUUID().toString());
        return "project/channel/rule-channel";
    }

    @RequestMapping("/rulechannelupdate")
    public String rulechannelupdate(Model model,String id) {
        model.addAttribute("ruleId", id);
        return "project/channel/rule-channel-update";
    }

    @RequestMapping("/selectchannel")
    public String selectchannel() {
        return "project/channel/select-channel";
    }

    @RequestMapping("/getChannelCount")
    public String getChannelCount(Model model) {
       // 询所有的渠道商
        List<ChannelManage> channelManageAll = channelManageService.findChannelManageAll();
        model.addAttribute("channelManageAll",channelManageAll);
        return "project/channel/channel-customer-count";
    }

}
