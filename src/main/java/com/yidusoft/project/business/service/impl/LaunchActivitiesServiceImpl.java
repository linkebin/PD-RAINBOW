package com.yidusoft.project.business.service.impl;


import com.alibaba.fastjson.JSON;
import com.yidusoft.core.AbstractService;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.dao.LaunchActivitiesMapper;
import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class LaunchActivitiesServiceImpl extends AbstractService<LaunchActivities> implements LaunchActivitiesService {
    @Resource
    private LaunchActivitiesMapper launchActivitiesMapper;

    @Resource
    LaunchActivitiesService launchActivitiesService;

    /**
     * 添加活动
     *
     * @param launchActivitiesJson
     * @return
     */
    @Override
    public Result addActivites(String launchActivitiesJson, HttpServletRequest request) throws UnknownHostException {
        LaunchActivities launchActivities = JSON.parseObject(launchActivitiesJson, LaunchActivities.class);
        launchActivities.setActivityCode(CodeHelper.getCode("LA"));
        launchActivities.setDeleted(0);
        launchActivities.setId(UUID.randomUUID().toString());
        launchActivities.setCreator(Security.getUser().getUserName());
        launchActivities.setUserId(Security.getUserId());
        launchActivities.setCreateTime(new Date());
        if (Security.getUser().getAccountType() == 0) {
            launchActivities.setInitiatorType(2);
            launchActivities.setActivityStatus(1);
        } else {
            int port = request.getServerPort();//获取服务器端口
            String ip = request.getServerName();//获取服务端ip
            launchActivities.setInitiatorType(1);
            launchActivities.setUestionnaireUri("http://" + ip + ":" + port + "/web/activities/fillingPage");
            launchActivities.setActivityPorn(CodeHelper.randomCode(8));
        }
        launchActivitiesService.save(launchActivities);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取用户的所有活动
     *
     * @param launchActivities
     * @return
     */
    @Override
    public List<LaunchActivities> getActivityAll(LaunchActivities launchActivities) {
        return launchActivitiesMapper.getActivityAll(launchActivities);
    }

    @Override
    public LaunchActivities getIdByPorn(LaunchActivities launchActivities) {
        return launchActivitiesMapper.getIdByPorn(launchActivities);
    }

    @Override
    public LaunchActivities getActivityById(String id) {
        return launchActivitiesMapper.getActivityById(id);
    }
}
