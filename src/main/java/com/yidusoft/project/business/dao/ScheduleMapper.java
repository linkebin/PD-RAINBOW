package com.yidusoft.project.business.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.business.domain.Schedule;

import java.util.List;

public interface ScheduleMapper extends Mapper<Schedule> {

    /**
     * 根据咨询师查询预约时间 or 事件
     * @param schedule
     * @return
     */
    List<Schedule> findScheduleDataTimeOrEvent(Schedule schedule);
}