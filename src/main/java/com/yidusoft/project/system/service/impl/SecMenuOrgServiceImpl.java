package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SecMenuOrgMapper;
import com.yidusoft.project.system.domain.SecMenuOrg;
import com.yidusoft.project.system.service.SecMenuOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zcb on 2017/9/4.
 */
@Service
@Transactional
public class SecMenuOrgServiceImpl extends AbstractService<SecMenuOrg> implements SecMenuOrgService {

    @Resource
    private SecMenuOrgMapper secMenuOrgMapper;

    @Override
    public void deleteOrgMenuAllByOrgId(String orgId) {
        secMenuOrgMapper.deleteOrgMenuAllByOrgId(orgId);
    }
}
