package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.yidusoft.utils.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        launchActivities.setActivityPorn(CodeHelper.randomCode(4));
        String url = "http://"+ip+"/web/activities/fillingPage?id="+launchActivities.getId();
        launchActivities.setUestionnaireUri(IpAddressUtils.getShortUrl(url));
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

    @Autowired
    private DataAcquisitionService dataAcquisitionService;
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
        launchActivities.setActivityEnd(new Date());
        list = launchActivitiesService.getActivityAll(launchActivities);
        int sumTotal=0;
        if(list!=null && list.size()>0){
            for(LaunchActivities la:list){
                if(!id.equals(la.getId())){
                    sumTotal+=la.getActivityTotal();
                   int used = dataAcquisitionService.findCountByActivityId(la.getId());
                    sumTotal-=used;

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
    /**
     * 查询所有的活动
     *活动名称、类型、状态（颜色区分-未完成、已完成、进行中）、区域、发起人、进度
     * @param
     * @return
     */
    @PostMapping("/getLaunchActivityAll")
    public Result getLaunchActivityAll(String param,Integer page,  Integer limit){
        Map<String,Object> map= (Map<String, Object>) JSONObject.parse(param);
        PageHelper.startPage(page, limit);
        List<Map<String, Object>>  list = launchActivitiesService.getLaunchActivityAll(map);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }



    /**
     * activity_presentation.html 页面头部
     * 查询活动的进度，还有相关统计信息
     * @param activityId
     * @return
     */
    @PostMapping("/getActivitySchedule")
    public Result   getActivitySchedule(String activityId){
        Map<String, Object> map= launchActivitiesService.getActivitySchedule(activityId);
        double userNumber=Double.valueOf(map.get("user_number").toString());
        double activityTotal=Double.valueOf(map.get("activity_total").toString());
        double  speedOfProgress=userNumber/activityTotal*100;
        map.put("speedOfProgress",speedOfProgress+"%");
        return ResultGenerator.genSuccessResult(map);
    }
    /**
     * 焦虑
     * activity_presentation.html 页面中部
     * 查询参与活动所有人相关问卷结论，性别，年龄
     * @param activityId
     * @param type
     * @return
     */
    @PostMapping("/getActivityNumberPeopleForResult")
    public Result   getActivityNumberPeopleForResult(String activityId,int type){
        Map<String, Object> map= launchActivitiesService.getActivityNumberPeopleForResult(activityId,type);
        return ResultGenerator.genSuccessResult(map);
    }
    @PostMapping("/getActivityNumberPeopleForResult_30")
    public Result   getActivityNumberPeopleForResult_30(String activityId,int type){
        Map<String, Object> map= launchActivitiesService.getActivityNumberPeopleForResult_30(activityId,type);
        return ResultGenerator.genSuccessResult(map);
    }

    /***
     * activity_presentation.html 页面下部
     * 查询活动问卷关于所有参与活动的人对应问题的答案，需要进行每题每项答案统计
     * @param activityId
     * @return
     */
    @PostMapping("/getQuestionForActivityQuestionnaire")
    public Result   getQuestionForActivityQuestionnaire(String activityId){
        List<Map<String,Object>>   mapList= launchActivitiesService.getQuestionForActivityQuestionnaire(activityId);
        return ResultGenerator.genSuccessResult(mapList);
    }

}
