package com.yidusoft.project.channelentrance.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.service.ChannelManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by smy on 2017/11/8.
 */
@RestController
@RequestMapping(value = "/channel/account")
public class ChannelAccountController {

    @Resource
    private ChannelManageService channelManageService;

    @PostMapping("/listByaccounts")
    public Result listByaccounts(Integer page, Integer pagesize, String json) {

        Map<String,Object> map = JSON.parseObject(json,Map.class);
        PageHelper.startPage(page, pagesize);
        List<Map<String,Object>> list = channelManageService.findChannelAccountListByChannelId(map);
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
