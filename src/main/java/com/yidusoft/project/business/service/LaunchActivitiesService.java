package com.yidusoft.project.business.service;

import com.yidusoft.core.Result;
import com.yidusoft.core.Service;
import com.yidusoft.project.business.domain.LaunchActivities;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.net.UnknownHostException;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface LaunchActivitiesService extends Service<LaunchActivities> {
    /**
     * 添加活动
     * @param launchActivitiesJson
     * @return
     */
    Result addActivites(String launchActivitiesJson,HttpServletRequest request) throws UnknownHostException;

    /**
     * 获取用户的所有活动
     * @param launchActivities
     * @return
     */
    List<LaunchActivities> getActivityAll(LaunchActivities launchActivities);

    /**
     * 通过活动邀请码获取问卷id和活动id
     * @param launchActivities
     * @return
     */
    LaunchActivities getIdByPorn(LaunchActivities launchActivities);

    /**
     *
     * @param id
     * @return
     */
    LaunchActivities getActivityById(String id);
}
