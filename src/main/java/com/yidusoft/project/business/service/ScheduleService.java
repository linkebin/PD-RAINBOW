package com.yidusoft.project.business.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.business.domain.Schedule;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface ScheduleService extends Service<Schedule> {
    /**
     * 根据咨询师查询预约时间 or 事件
     * @param schedule
     * @return
     */
    List<Schedule> findScheduleDataTimeOrEvent(Schedule schedule);

    /**
     * 查询今天往后30天的预约记录
     * @param schedule
     * @return
     */
    List<Schedule> findScheduleToBackDay30(Schedule schedule);

}
