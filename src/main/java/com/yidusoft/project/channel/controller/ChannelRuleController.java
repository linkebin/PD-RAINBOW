package com.yidusoft.project.channel.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.AccountRule;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.domain.ChannelRule;
import com.yidusoft.project.channel.domain.ClearingManage;
import com.yidusoft.project.channel.service.AccountRuleService;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.channel.service.ChannelRuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.channel.service.ClearingManageService;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import com.yidusoft.utils.DateUtils;
import com.yidusoft.utils.Security;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

    @Resource
    private AccountRuleService accountRuleService;

    @Resource
    private ClearingManageService clearingManageService;

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


    @PostMapping("/saveClearing")
    public Result saveClearing(String listMap,String channelId) {
        List<Map<String,Object>> maps = parseJsonArrayStrToListForMaps(listMap);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("channel_id",channelId);
        List<Map<String,Object>> rules = channelRuleService.findRuleByChannel(map);
        AccountRule accountRule = accountRuleService.findBy("defaultRule",1);

        List<ClearingManage> clearingManageList = new ArrayList<ClearingManage>();

        for(Map<String,Object> m :maps){
            String sdata1 = m.get("paymentTime").toString();
            ClearingManage clearingManage = new ClearingManage();
            clearingManage.setClearId(UUID.randomUUID().toString());
            clearingManage.setOrderId(m.get("orderId").toString());
            clearingManage.setChannelId(channelId);
            clearingManage.setCreateTime(new Date());
            clearingManage.setCreator(Security.getUser().getUserName());
            clearingManage.setStatus(1);
            BigDecimal money=new BigDecimal(m.get("orderMoney").toString());

            boolean isIn = true;
            for(Map<String,Object> ru :rules){
                boolean f = DateUtils.isOverlap(DateUtils.stampToDate(sdata1),DateUtils.stampToDate(sdata1),
                        ru.get("start_time").toString(),ru.get("end_time").toString());
                if (f){
                    isIn = false;
                    clearingManage.setRuleId(ru.get("rule_id").toString());

                    BigDecimal rum=new BigDecimal(ru.get("rule").toString());
                    System.out.println(money.multiply(rum));
                    clearingManage.setBrokerage(money.multiply(rum));
                    clearingManageList.add(clearingManage);
                }
            }
            if (isIn){
                clearingManage.setRuleId(accountRule.getRuleId());
                BigDecimal rum=new BigDecimal(accountRule.getRule());
                System.out.println(money.multiply(rum));
                clearingManage.setBrokerage(money.multiply(rum));
                System.out.println(money.multiply(rum));
                clearingManageList.add(clearingManage);
            }
        }
        try {
            clearingManageService.clearingOrder(clearingManageList);
            return ResultGenerator.genSuccessResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("结算失败");
    }

    //orgJsonUtil
    public static List<Map<String, Object>> parseJsonArrayStrToListForMaps(String jsonArrayStr) {
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        try {
            if(jsonArrayStr != null) {
                JSONArray jsonArray = new JSONArray(jsonArrayStr);
                Map<String, Object> map = null;
                for(int j=0;j<jsonArray.length();j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    map = parseJsonObjectStrToMap(jsonObject.toString());
                    if(map != null) {
                        list.add(map);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(list.size() == 0) {
            return null;
        }
        return list;
    }

    //把JsonObject的字符串转换成Map<String, Object>
    public static Map<String, Object> parseJsonObjectStrToMap(String jsonObjectStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(jsonObjectStr != null) {
                JSONObject jsonObject = new JSONObject(jsonObjectStr);
                for(int j=0;j<jsonObject.length();j++) {
                    Iterator<String> iterator = jsonObject.keys();
                    while(iterator.hasNext()) {
                        String key = iterator.next();
                        Object value = jsonObject.get(key);
                        map.put(key, value);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(map.size() == 0) {
            return null;
        }
        return map;
    }
}
