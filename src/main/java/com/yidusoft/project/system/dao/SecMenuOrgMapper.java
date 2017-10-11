package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecMenuOrg;

/**
 * Created by zcb on 2017/9/4.
 */
public interface SecMenuOrgMapper extends Mapper<SecMenuOrg> {

    /**
     * 根据公司组织id删除该公司的全部菜单
     * @param orgId
     */
    void deleteOrgMenuAllByOrgId(String orgId);
}
