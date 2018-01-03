package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import com.yidusoft.utils.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
@RestController
@RequestMapping("/launch/activities")
public class LaunchActivitiesController {
    @Resource
    private LaunchActivitiesService launchActivitiesService;

    /**
     * 添加活动
     *
     * @param launchActivitiesJson
     * @return
     */
    @PostMapping("/add")
    public Result add(String launchActivitiesJson, HttpServletRequest request) throws UnknownHostException {
        return launchActivitiesService.addActivites(launchActivitiesJson,request);
    }

    /**
     * 获取用户的
     *
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/listByParam")
    public Result listByParam(Integer page, Integer size, String json) {
        PageHelper.startPage(page, size);
        List<LaunchActivities> list = new ArrayList<>();
        LaunchActivities launchActivities=JSON.parseObject(json,LaunchActivities.class);
        list = launchActivitiesService.getActivityAll(launchActivities);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获取活动详情
     *
     * @param id
     * @return
     */
    @PostMapping("/detail")
    public Result detail(String id) {
        LaunchActivities launchActivities = launchActivitiesService.findById(id);
        return ResultGenerator.genSuccessResult(launchActivities);
    }

    /**
     * 修改活动
     *
     * @param launchActivitiesJson
     * @return
     */
    @PostMapping("/update")
    public Result update(String launchActivitiesJson) {
        LaunchActivities launchActivities = JSON.parseObject(launchActivitiesJson, LaunchActivities.class);
        launchActivitiesService.update(launchActivities);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 批量删除活动
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Result delete(String ids) {
        String arr[] = ids.split(",");
        for (String str : arr) {
            LaunchActivities launchActivities = launchActivitiesService.findById(str);
            launchActivities.setDeleted(1);
            launchActivitiesService.update(launchActivities);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 活动审批
     * @param id
     * @return
     */
    @PostMapping("/approval")
    public Result approval(String id,HttpServletRequest request) throws UnknownHostException {
        //获取服务器IP
        String ip = request.getServerName();//获取服务端ip
        LaunchActivities launchActivities=launchActivitiesService.findById(id);
        launchActivities.setActivityStatus(2);
        launchActivities.setActivityPorn(CodeHelper.randomCode(8));
        launchActivities.setUestionnaireUri("http://"+ip+":/web/activities/fillingPage");
        launchActivitiesService.update(launchActivities);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 发送注册手机验证码
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/code")
    @ResponseBody
    public Result signcode(HttpServletRequest httpServletRequest,String mobile){
        try{
            String json = SendMessageCode.sendMessageCode(mobile);
            SMSCode code = JSON.parseObject(json,SMSCode.class);
            if (code.getCode() == 200){
                httpServletRequest.getSession().setAttribute("signCode",code.getObj());
            }else{
                return ResultGenerator.genFailResult("验证码发送失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("验证码发送失败");
        }
        return ResultGenerator.genSuccessResult().setMessage("验证码发生成功");
    }

    /**
     * 获取用户所有活动的人数
     * @param id
     * @return
     */
    @PostMapping("/getSumTotal")
    public Result getSumTotal(String id) {
        List<LaunchActivities> list = new ArrayList<>();
        LaunchActivities launchActivities=new LaunchActivities();
        launchActivities.setUserId(Security.getUserId());
        list = launchActivitiesService.getActivityAll(launchActivities);
        int sumTotal=0;
        if(list!=null && list.size()>0){
            for(LaunchActivities la:list){
                if(!id.equals(la.getId())){
                    sumTotal+=la.getActivityTotal();
                }
            }
        }
        return ResultGenerator.genSuccessResult(sumTotal);
    }

    /**
     * 获取还在活动时间内的问卷id
     * @param json
     * @return
     */
    @PostMapping("/getIdByPorn")
    public Result getIdByPorn(String json){
        LaunchActivities launchActivities = JSON.parseObject(json,LaunchActivities.class);
        launchActivities=launchActivitiesService.getIdByPorn(launchActivities);
        return ResultGenerator.genSuccessResult(launchActivities);
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/getActivityById")
    public Result getActivityById(String id){
        LaunchActivities launchActivities=launchActivitiesService.getActivityById(id);
        return ResultGenerator.genSuccessResult(launchActivities);
    }
}
