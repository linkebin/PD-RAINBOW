package com.yidusoft.project.system.service;
import com.yidusoft.project.system.domain.Backlog;
import com.yidusoft.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/12/19.
 */
public interface BacklogService extends Service<Backlog> {

    List<Map<String,Object>> findMyBackLogList(Map<String,Object> map);
}
