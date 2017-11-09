package com.yidusoft.project.monitor.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.monitor.domain.OperLog;

import java.util.List;

public interface OperLogMapper extends Mapper<OperLog> {
  //分页条件查询此行为记录
   List<OperLog> operLogListByPage(OperLog operLog);
}