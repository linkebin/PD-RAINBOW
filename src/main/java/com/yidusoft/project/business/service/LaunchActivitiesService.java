package com.yidusoft.project.business.service;

import com.yidusoft.core.Result;
import com.yidusoft.core.Service;
import com.yidusoft.project.business.domain.LaunchActivities;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;


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

    /***
     * 查询活动问卷关于所有参与活动的人对应问题的答案，需要进行每题每项答案统计
     * @param activityId
     * @return
     */
    List<Map<String,Object>> getQuestionForActivityQuestionnaire(String activityId);

    /**
     * 查询活动的进度，还有相关统计信息
     * @param activityId
     * @return
     */
    Map<String,Object> getActivitySchedule( String activityId);
    /**
     * 查询参与活动所有人相关问卷结论，性别，年龄
     * @param activityId
     * @return
     */
    Map<String,Object> getActivityNumberPeopleForResult( String activityId,int type);

    /**
     * 查询参与活动所有人相关问卷结论，性别，年龄
     * @param activityId
     * @return
     */
    Map<String,Object> getActivityNumberPeopleForResult_30( String activityId,int type);



    /**
     * 查询所有的活动
     *活动名称、类型、状态（颜色区分-未完成、已完成、进行中）、区域、发起人、进度
     * @param map
     * @return
     */
    List<Map<String,Object>>   getLaunchActivityAll(Map map);

}
