package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecMenuOrg;

/**
 * Created by zcb on 2017/9/4.
 */
public interface SecMenuOrgService  extends Service<SecMenuOrg> {

    /**
     * 根据公司组织id删除该公司的全部菜单
     * @param orgId
     */
    void deleteOrgMenuAllByOrgId(String orgId);
}
