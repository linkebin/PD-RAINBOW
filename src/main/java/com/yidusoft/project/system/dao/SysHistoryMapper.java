package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.SysHistory;

import java.util.List;

public interface SysHistoryMapper extends Mapper<SysHistory> {

    /**
     * 获取近期选择的用户列表
     * @param sysHistory 用户id和类型
     * @return
     */
    List<SecUser> findRecentSelectLeadingByUserIdList(SysHistory sysHistory);
}