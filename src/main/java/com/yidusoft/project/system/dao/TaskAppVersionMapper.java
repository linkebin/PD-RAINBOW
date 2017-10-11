package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.TaskAppVersion;

import java.util.List;

/**
 * Created by smy on 2017/9/7.
 */
public interface TaskAppVersionMapper extends Mapper<TaskAppVersion> {

    List<TaskAppVersion> findAppVersionList();
}
