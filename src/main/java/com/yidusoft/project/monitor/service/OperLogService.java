package com.yidusoft.project.monitor.service;
import com.yidusoft.project.monitor.domain.OperLog;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface OperLogService extends Service<OperLog> {
    //分页条件查询此行为记录
    List<OperLog> operLogListByPage(OperLog operLog);
}
