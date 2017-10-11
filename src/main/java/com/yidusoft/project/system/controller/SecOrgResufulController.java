package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecOrgService;
import com.yidusoft.project.system.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by you on 2017/8/20.
 */
@RestController
@RequestMapping("sec/org")
public class SecOrgResufulController {
    @Autowired
    private SecOrgService secOrgService;

    @Autowired
    private SecUserService secUserService;

    @PostMapping("/userTree")
    public Result findUserTree(String orgId){

        return ResultGenerator.genSuccessResult(secOrgService.findOrgChildren(orgId));
    }

    @PostMapping("/users")
    public Result findOrgUsers(String orgId, int page, int size){

        int  total = secUserService.findOrgUserList(orgId).size();
        PageHelper.startPage(page, size);

        List<SecUser> secUserList = secUserService.findOrgUserList(orgId);

        PageInfo pageInfo = new PageInfo(secUserList);
//        int i = total%size ==0 ? total/size : (total/size)+1;
//        pageInfo.setPages(i);
        pageInfo.setTotal(total);

        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/orgByUsers")
    public Result orgByUsers(String json, int page, int size){
          SecUser secUser = JSON.parseObject(json,SecUser.class);

        int  total = secUserService.findOrgByUsers(secUser).size();
        PageHelper.startPage(page, size);

        List<SecUser> secUserList = secUserService.findOrgByUsers(secUser);
        PageInfo pageInfo = new PageInfo(secUserList);
        int i = total%size ==0 ? total/size : (total/size)+1;
        pageInfo.setPages(i);

        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
