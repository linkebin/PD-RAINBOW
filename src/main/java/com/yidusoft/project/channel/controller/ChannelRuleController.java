package com.yidusoft.project.channel.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.domain.ChannelRule;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.channel.service.ChannelRuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/channel/rule")
public class ChannelRuleController {
    @Resource
    private ChannelRuleService channelRuleService;

    @Resource
    private ChannelManageService channelManageService;

    @Resource
    private OrderOnlineService orderOnlineService;

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

    @PostMapping("/deleteOne")
    public Result deleteOne(String json) {
        Map<String,Object> map = JSON.parseObject(json,Map.class);
        channelRuleService.deletedOne(map);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/listNoClearing")
    public Result listNoClearing(Integer page,  Integer limit,String json) {
        Map<String,Object> map = JSON.parseObject(json,Map.class);

        List<String> ids = getChannelConsultIds(map);
        List<Map<String,Object>> list = null;
        if (ids.size()>0){
            PageHelper.startPage(page, limit);
             list = orderOnlineService.findOrderByUserId(ids,map);
        }

        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }

    public List<String> getChannelConsultIds(Map<String,Object> map){
        List<String> ids = new ArrayList<String>();
        List<Map<String,Object>> list = channelManageService.findChannelOrAccountCounselorListByParameter(map);
        for (Map<String,Object> m : list){
            ids.add(m.get("ID_").toString());
        }
        return ids;
    }


}
