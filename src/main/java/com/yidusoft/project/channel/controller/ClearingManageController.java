package com.yidusoft.project.channel.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.ClearingManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.channel.service.ClearingManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2017/12/01.
*/
@RestController
@RequestMapping("/clearing/manage")
public class ClearingManageController {
    @Resource
    private ClearingManageService clearingManageService;

    @Resource
    private ChannelManageService channelManageService;

    @PostMapping
    public Result add(ClearingManage clearingManage) {
        clearingManageService.save(clearingManage);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        clearingManageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(ClearingManage clearingManage) {
        clearingManageService.update(clearingManage);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/clearingLineChart")
    public Result clearingLineChart(String json) {
        Map<String,Object> map = JSON.parseObject(json,Map.class);

        List<Map<String,Object>> maps = clearingManageService.findChannelAccountLineChart(null,map);

        return ResultGenerator.genSuccessResult(maps);
    }

    @PostMapping("/channelUserLineChart")
    public Result channelUserLineChart(String json) {
        Map<String,Object> map = JSON.parseObject(json,Map.class);
        List<String> ids = getChannelConsultIds(map);
        map.put("channel_id", Security.getUser().getChannelId());
        List<Map<String,Object>> maps = null;
        if (ids.size()>0){
            maps = clearingManageService.findChannelAccountLineChart(ids,map);
        }
        return ResultGenerator.genSuccessResult(maps);
    }

    @PostMapping("/listHasClearing")
    public Result listHasClearing(Integer page,  Integer limit,String json) {
        Map<String,Object> map = JSON.parseObject(json,Map.class);

        List<String> ids = getChannelConsultIds(map);
        List<Map<String,Object>> list = null;
        if (ids.size()>0){
            PageHelper.startPage(page, limit);
            list = clearingManageService.findHasClearingByChannelCounselorId(ids,map);
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
