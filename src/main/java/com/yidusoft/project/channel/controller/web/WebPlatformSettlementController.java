package com.yidusoft.project.channel.controller.web;

import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.PlatformSettlement;
import com.yidusoft.project.channel.service.PlatformSettlementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2017/12/13.
*/
@Controller
@RequestMapping("/web/platform/settlement")
public class WebPlatformSettlementController {
    @Resource
    private PlatformSettlementService platformSettlementService;

    @RequestMapping("/platformSettlement")
    public String platformSettlement() {
        return "project/channel/platform-settlement";
    }

    @RequestMapping("/channelInfo")
    public String channelInfo(String ID, Model model) {
        model.addAttribute("ID",ID);
        return "project/channel/platformSettlement-channelInfo";
    }

    @RequestMapping("/reconciliation")
    public String reconciliation() {
        return "project/channel/platform-reconciliation";
    }
}
