package com.yidusoft.project.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.Scene;
import com.yidusoft.project.questionnaire.service.QuestionnairePermissionMiddleService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhdj on 2017/11/14.
 */
@RestController
@RequestMapping("/questionnairePermissionMiddle")
public class QuestionnairePermissionMiddleController {
    @Resource
    private QuestionnairePermissionMiddleService questionnairePermissionMiddleService;

    @Resource
    private SecUserService secUserService;

    /**
     * 查询所有的咨询师
     * @param params
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findQuestionnaireSecUser")
    public Result findQuestionnaireSecUser(String params, int page, int size){
        SecUser secUser = JSON.parseObject(params, SecUser.class);
        PageHelper.startPage(page, size);
        List<SecUser> list = questionnairePermissionMiddleService.findQuestionnaireSecUser(secUser);
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 查询指定id的咨询师
     * @param ids
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findQuestionnaireSecUserByIds")
    public Result findQuestionnaireSecUserByIds(String ids, int page, int size){
        String[] userIds = ids.split(",");
        List<SecUser> list = new ArrayList<>();
        for (int i = 0; i < userIds.length; i++){
           SecUser secUser = secUserService.findById(userIds[i]);
           list.add(secUser);
        }
        PageHelper.startPage(page, size);
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
