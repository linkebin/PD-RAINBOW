package com.yidusoft.project.business.service.impl;


import com.yidusoft.core.AbstractService;
import com.yidusoft.project.business.dao.LaunchActivitiesMapper;
import com.yidusoft.project.business.domain.LaunchActivities;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class LaunchActivitiesServiceImpl extends AbstractService<LaunchActivities> implements LaunchActivitiesService {
    @Resource
    private LaunchActivitiesMapper launchActivitiesMapper;

}
