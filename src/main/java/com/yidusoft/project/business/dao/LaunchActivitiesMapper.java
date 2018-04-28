package com.yidusoft.project.business.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.business.domain.LaunchActivities;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LaunchActivitiesMapper extends Mapper<LaunchActivities> {
    /**
     * 获取用户的所有活动
     * @param launchActivities
     * @return
     */
    List<LaunchActivities> getActivityAll(LaunchActivities launchActivities);

    LaunchActivities getIdByPorn(LaunchActivities launchActivities);

    LaunchActivities getActivityById(String id);

    /**
     * 查询活动的进度，还有相关统计信息
     * @param activityId
     * @return
     */
    Map<String,Object> getActivitySchedule(@Param(value = "activityId") String activityId);

    /**
     * 查询参与活动所有人相关问卷结论，性别，年龄
     * @param activityId
     * @return
     */
    List<Map<String,Object>>  getActivityNumberPeopleForResult(@Param(value = "activityId") String activityId);

    /**
     * 查询所有的活动
     *活动名称、类型、状态（颜色区分-未完成、已完成、进行中）、区域、发起人、进度
     * @param map
     * @return
     */
    List<Map<String,Object>>   getLaunchActivityAll(Map<String,Object> map);
}