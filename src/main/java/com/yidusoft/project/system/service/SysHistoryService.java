package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.SysHistory;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/09/08.
 */
public interface SysHistoryService extends Service<SysHistory> {

    /**
     * 获取近期选择的用户列表
     * @param sysHistory 用户id和类型
     * @return
     */
    List<SecUser> findRecentSelectLeadingByUserIdList(SysHistory sysHistory);

}
