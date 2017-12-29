package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.Backlog;

import java.util.List;
import java.util.Map;

public interface BacklogMapper extends Mapper<Backlog> {

    List<Map<String,Object>> findMyBackLogList(Map<String,Object> map);
}