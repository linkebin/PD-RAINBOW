package com.yidusoft.project.channel.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/channel/manage")
public class ChannelManageController {
    @Resource
    private ChannelManageService channelManageService;

    @PostMapping("/add")
    public Result add(String json) {
        ChannelManage channelManage = JSON.parseObject(json,ChannelManage.class);
        channelManage.setId(UUID.randomUUID().toString());
        channelManage.setChannelCode(CodeHelper.getCode("QD"));
        channelManage.setCreator(Security.getUser().getUserName());
        channelManageService.save(channelManage);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        channelManageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String  json) {
        ChannelManage channelManage = JSON.parseObject(json,ChannelManage.class);
        channelManageService.update(channelManage);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/deleteBacth")
    public Result deleteBacth(String ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            ChannelManage channelManage = channelManageService.findById(str);
            if (channelManage!=null){
                channelManage.setDeleted(1);
                channelManageService.update(channelManage);
            }
        }
        return ResultGenerator.genSuccessResult("删除成功！");
    }

    @PostMapping("/detail")
    public Result detail(String id) {
        ChannelManage channelManage = channelManageService.findById(id);
        return ResultGenerator.genSuccessResult(channelManage);
    }

    @PostMapping("/listByparameter")
    public Result list(int page,  int pagesize,String json) {

        ChannelManage channelManage = JSON.parseObject(json,ChannelManage.class);
        PageHelper.startPage(page, pagesize);
        List<ChannelManage> list = channelManageService.finndChannelByParameterList(channelManage);
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
