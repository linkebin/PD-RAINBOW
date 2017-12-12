package com.yidusoft.project.business.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.business.domain.VisitingRecord;

import java.util.List;
import java.util.Map;

public interface VisitingRecordMapper extends Mapper<VisitingRecord> {


    //来访目的区域统计
    List<Map<String,Object>> findComeToCallGoalAreaCount(Map<String,Object> map);

    /**
     * 根据客户id 查询客户来访时间轴
     * @param visitingRecord
     * @return
     */
    List<VisitingRecord> findVisitingRecordShaftTimeByCustomerId(VisitingRecord visitingRecord);

    /**
     * 来访管理列表sql
     * @param visitingRecord
     * @return
     */
    List<Map<String,Object>> findVisitingRecordByParameter(VisitingRecord visitingRecord);

    /**
     * 咨询师查询来访表和预约表集合-合并
     * @param map
     * @return
     */
    List<Map<String,Object>> findVisitorAndScheduleByParameter(Map<String,Object> map);

    /**
     * 查询来访者的来访的总次数
     * @param userId
     * @return
     */
    int  getVisitingTotal(String userId);


    //获取来访目的条形图数据
    List<Map<String,Object>> findGoalBarChart(Map<String,Object> map);

}