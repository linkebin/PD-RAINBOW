package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import com.yidusoft.utils.CodeHelper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static java.net.InetAddress.getLocalHost;

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
    public Result add(String launchActivitiesJson, Session session) {
        return launchActivitiesService.addActivites(launchActivitiesJson,session);
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
        int port = request.getServerPort();
        String addr = getLocalHost().getHostAddress();
        LaunchActivities launchActivities=launchActivitiesService.findById(id);
        launchActivities.setActivityStatus(1);
        launchActivities.setActivityPorn(CodeHelper.randomCode(8));
        launchActivities.setUestionnaireUri("http://"+addr+":"+port+"/web/activities/fillingPage");
        launchActivitiesService.update(launchActivities);
        return ResultGenerator.genSuccessResult();
    }
}
