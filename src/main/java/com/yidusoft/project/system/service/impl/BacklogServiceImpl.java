package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.BacklogMapper;
import com.yidusoft.project.system.domain.Backlog;
import com.yidusoft.project.system.service.BacklogService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/12/19.
 */
@Service
@Transactional
public class BacklogServiceImpl extends AbstractService<Backlog> implements BacklogService {
    @Resource
    private BacklogMapper backlogMapper;

    @Override
    public List<Map<String, Object>> findMyBackLogList(Map<String, Object> map) {
        return backlogMapper.findMyBackLogList(map);
    }
}
