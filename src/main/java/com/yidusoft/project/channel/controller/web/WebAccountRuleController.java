package com.yidusoft.project.channel.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.AccountRule;
import com.yidusoft.project.channel.service.AccountRuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@Controller
@RequestMapping("/web/account/rule")
public class WebAccountRuleController {
    @Resource
    private AccountRuleService accountRuleService;

    @RequestMapping("/openlist")
    public String openlist() {

        return "project/channel/rule-list";
    }


}
