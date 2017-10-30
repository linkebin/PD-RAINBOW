package com.yidusoft.project.channel.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.AccountRule;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.AccountRuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/account/rule")
public class AccountRuleController {
    @Resource
    private AccountRuleService accountRuleService;

    @PostMapping("/add")
    public Result add(String json) {
        AccountRule accountRule = JSON.parseObject(json,AccountRule.class);
        accountRule.setRuleId(UUID.randomUUID().toString());
        accountRule.setCreator(Security.getUser().getUserName());
        accountRule.setDeleted(0);
        accountRule.setCreateTime(new Date());

        if (accountRule.getDefaultRule() ==1){
            accountRuleService.deleteDefaultRuleAll();
        }

        accountRuleService.save(accountRule);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        accountRuleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String json) {
        AccountRule accountRule = JSON.parseObject(json,AccountRule.class);
        try {
            accountRuleService.update(accountRule);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("更新失败！");
        }

        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/deleteBacth")
    public Result deleteBacth(String ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            AccountRule accountRule = accountRuleService.findById(str);
            if (accountRule!=null){
                if (accountRule.getDefaultRule()!=1){
                    accountRule.setDeleted(1);
                }
                accountRuleService.update(accountRule);
            }
        }
        return ResultGenerator.genSuccessResult("删除成功！");
    }
    @PostMapping("/detail")
    public Result detail(String id) {
        AccountRule accountRule = accountRuleService.findById(id);
        return ResultGenerator.genSuccessResult(accountRule);
    }


    @PostMapping("/listByparameter")
    public Result list(int page, int pagesize,String json) {
        AccountRule accountRule = JSON.parseObject(json,AccountRule.class);
        PageHelper.startPage(page, pagesize);
        List<AccountRule> list = accountRuleService.finndAccountRuleByParameterList(accountRule);

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
