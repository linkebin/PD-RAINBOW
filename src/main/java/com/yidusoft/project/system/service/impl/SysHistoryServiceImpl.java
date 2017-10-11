package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SysHistoryMapper;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.SysHistory;
import com.yidusoft.project.system.service.SysHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/09/08.
 */
@Service
@Transactional
public class SysHistoryServiceImpl extends AbstractService<SysHistory> implements SysHistoryService {
    @Resource
    private SysHistoryMapper sysHistoryMapper;

    @Override
    public List<SecUser> findRecentSelectLeadingByUserIdList(SysHistory sysHistory) {
        return sysHistoryMapper.findRecentSelectLeadingByUserIdList(sysHistory);
    }
}
