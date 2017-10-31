package com.yidusoft.project.channel.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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
        channelManage.setDeleted(0);
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

    @PostMapping("/listByaccounts")
    public Result listByaccounts(Integer page,  Integer limit,String json) {

        Map<String,Object> map = JSON.parseObject(json,Map.class);

        PageHelper.startPage(page, limit);
        List<Map<String,Object>> list = channelManageService.findChannelAccountListByChannelId(map);
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }
    @Autowired
    private SecUserService secUserService;

    @PostMapping("/channelAccountAdd")
    public Result channelAccountAdd(String params) {
        SecUser secUser = JSON.parseObject(params,SecUser.class);
        secUser.setCreator(Security.getUser().getUserName());
        secUser.setHeadImg("/upload/headImg/default-pic.png");
        String inviterCode = CodeHelper.randomCode(8);
        SecUser isUser = null;
        if (inviterCode!=null){
            isUser = secUserService.findSecUserByInviterCode(inviterCode);
        }
        if (isUser != null){
            channelAccountAdd(params);
        }else {
            secUser.setInviterCode(inviterCode);
            return  secUserService.addUser(JSON.toJSONString(secUser));
        }
        return ResultGenerator.genFailResult("添加失败");
    }

    @PostMapping("/accountInfo")
    public Result accountInfo(String id) {
        SecUser secUser = secUserService.findById(id);
        return ResultGenerator.genSuccessResult(secUser);
    }

    @PostMapping("/updateaccountInfo")
    public Result updateaccountInfo(String json) {
        SecUser secUser = JSON.parseObject(json,SecUser.class);
        try {
            secUserService.update(secUser);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("编辑失败！");
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/accounttree")
    public Result accounttree() {
        return ResultGenerator.genSuccessResult(channelManageService.findChannelAccountTree());
    }

    @PostMapping("/listByAccountCounselor")
    public Result listByAC(Integer page,  Integer limit,String json) {

        Map<String,Object> map = JSON.parseObject(json,Map.class);

        PageHelper.startPage(page, limit);
        List<Map<String,Object>> list = channelManageService.findChannelOrAccountCounselorListByParameter(map);
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }
}
