package com.yidusoft.project.channel.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.PlatformSettlement;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.channel.service.PlatformSettlementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2017/12/13.
*/
@RestController
@RequestMapping("/platform/settlement")
public class PlatformSettlementController {
    @Resource
    private PlatformSettlementService platformSettlementService;

    @Resource
    private ChannelManageService channelManageService;

    @PostMapping
    public Result add(PlatformSettlement platformSettlement) {
        platformSettlementService.save(platformSettlement);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/areaAndChannelOrderList")
    public Result areaAndChannelOrderList(Integer page,  Integer limit,String json) {

        Map<String,Object> map = JSON.parseObject(json,Map.class);
        List<Map<String,Object>> channels = channelManageService.findChannelListAndTypeAndParameter(map);
        List<String> ids = new ArrayList<String>();
        for (Map<String,Object> m3 : channels){
            ids.add(m3.get("id_").toString());
        }
        if (ids.size() == 0){
            ids.add("9x991000");
        }

        List<Map<String,Object>> list = platformSettlementService.findAreaAndChannelOrderList(ids,map);
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }

    @PostMapping("/areaChannelTrees")
    public Result areaChannelTrees() {
        List<Map<String,Object>> areaChannelTrees = channelManageService.findAreaChannelTree();
        return ResultGenerator.genSuccessResult(areaChannelTrees);
    }

    @PostMapping("/payment")
    public Result detail(String json) {
        Map<String,Object> map = JSON.parseObject(json,Map.class);
        PlatformSettlement platformSettlement = platformSettlementService.findById( map.get("ID_").toString());
        BigDecimal now = new BigDecimal(map.get("money").toString());
        platformSettlement.setStatus("3");
        if (platformSettlement.getUnliquidated().compareTo(now) == 0){
            platformSettlement.setStatus("2");
        }
        platformSettlement.setClosed(platformSettlement.getClosed().add(now));
        platformSettlement.setUnliquidated(platformSettlement.getUnliquidated().subtract(now));
        try {
            platformSettlementService.update(platformSettlement);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("结算失败");
        }
        return ResultGenerator.genSuccessResult().setMessage("结算成功");
    }

    @PostMapping("/list")
    public Result list(String json) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map<String,Object>>  list = platformSettlementService.findPlatformSettlementList(map);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }

    @PostMapping("/channelInfos")
    public Result channelInfos(String json) {
        Map<String,Object> map = JSON.parseObject(json,Map.class);
        String id = map.get("ID").toString();
        PlatformSettlement platformSettlement = platformSettlementService.findById(id);
        int month = (Integer.parseInt(platformSettlement.getMonth())+1);
        map.put("create_time",platformSettlement.getYear()+"-"+month);
        if (month<10){
            map.put("create_time",platformSettlement.getYear()+"-0"+month);
        }

        PageHelper.startPage(1, 1000);
        List<Map<String,Object>>  list = platformSettlementService.findPlatformSettlementChannelInfo(map);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }
}
