package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.TaskAppVersionMapper;
import com.yidusoft.project.system.domain.TaskAppVersion;
import com.yidusoft.project.system.service.TaskAppVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by smy on 2017/9/7.
 */
@Service
@Transactional
public class TaskAppVersionServiceImpl extends AbstractService<TaskAppVersion> implements TaskAppVersionService {

    @Resource
    private TaskAppVersionMapper taskAppVersionMapper;

    @Override
    public List<TaskAppVersion> findAppVersionList() {
        return taskAppVersionMapper.findAppVersionList();
    }
}
