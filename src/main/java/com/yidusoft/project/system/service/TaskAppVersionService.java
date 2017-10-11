package com.yidusoft.project.system.service;

import com.yidusoft.project.system.domain.TaskAppVersion;

import java.util.List;

/**
 * Created by smy on 2017/9/7.
 */
public interface TaskAppVersionService extends com.yidusoft.core.Service<TaskAppVersion> {

    List<TaskAppVersion> findAppVersionList();
}
