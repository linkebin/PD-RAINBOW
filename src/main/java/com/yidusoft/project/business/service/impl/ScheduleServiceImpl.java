package com.yidusoft.project.business.service.impl;
import com.yidusoft.core.AbstractService;
import com.yidusoft.project.business.dao.ScheduleMapper;
import com.yidusoft.project.business.domain.Schedule;
import com.yidusoft.project.business.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class ScheduleServiceImpl extends AbstractService<Schedule> implements ScheduleService {
    @Resource
    private ScheduleMapper scheduleMapper;

}
