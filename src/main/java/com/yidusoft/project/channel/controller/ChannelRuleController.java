package com.yidusoft.project.channel.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.domain.ChannelRule;
import com.yidusoft.project.channel.service.ChannelRuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/channel/rule")
public class ChannelRuleController {
    @Resource
    private ChannelRuleService channelRuleService;

    @PostMapping("/add")
    public Result add(String channelIds,String ruleId) {
           String arr [] = channelIds.split(",");
        for (String s:arr) {
            ChannelRule channelRule = new ChannelRule();
            channelRule.setId(UUID.randomUUID().toString());
            channelRule.setChannelId(s);
            channelRule.setRuleId(ruleId);
            channelRuleService.save(channelRule);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping(value = "/deleteAll")
    public Result deleteAll(String  ruleId) {
        try {
            channelRuleService.deleteRuleChannelAll(ruleId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("切换时间失败");
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/channelRuleList")
    public Result listByparameterSelect(int page,  int pagesize,String json) {
        Map map = JSON.parseObject(json,Map.class);
        PageHelper.startPage(page, pagesize);
        List<Map<String,Object>> maps = channelRuleService.findChannelByRuleIdList(map);

        PageInfo pageInfo = new PageInfo(maps);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PutMapping
    public Result update(ChannelRule channelRule) {
        channelRuleService.update(channelRule);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        ChannelRule channelRule = channelRuleService.findById(id);
        return ResultGenerator.genSuccessResult(channelRule);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ChannelRule> list = channelRuleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
