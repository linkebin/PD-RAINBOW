package com.yidusoft.project.business.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.business.domain.LaunchActivities;

import java.util.List;

public interface LaunchActivitiesMapper extends Mapper<LaunchActivities> {
    /**
     * 获取用户的所有活动
     * @param launchActivities
     * @return
     */
    List<LaunchActivities> getActivityAll(LaunchActivities launchActivities);

    LaunchActivities getIdByPorn(LaunchActivities launchActivities);
}