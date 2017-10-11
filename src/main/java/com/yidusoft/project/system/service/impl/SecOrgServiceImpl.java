package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SecOrgMapper;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecOrg;
import com.yidusoft.project.system.domain.SecOrgForParameter;
import com.yidusoft.project.system.service.SecOrgService;
import com.yidusoft.utils.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
@Service
@Transactional
public class SecOrgServiceImpl extends AbstractService<SecOrg> implements SecOrgService {
    @Resource
    private SecOrgMapper secOrgMapper;

    @Override
    public String findOrgParentStringIds(String orgId) {
        return secOrgMapper.findOrgParentStringIds(orgId);
    }

    public List<TreeNode> getTree(String userId){

        return secOrgMapper.getTree(userId);
    }
    
    @Override
    public List<SecOrg> getSecOrgList(SecOrg secOrg) {
        return secOrgMapper.getSecOrgList(secOrg);
    }

    @Override
    public List<SecOrg> findOrgChildren(String orgId) {
        return secOrgMapper.findOrgChildren(orgId);
    }

    @Override
    public int getQueryAllTotal(SecOrgForParameter secOrgForParameter) {
        return secOrgMapper.getQueryAllTotal(secOrgForParameter);
    }

    @Override
    public List<SecOrg> getQueryAll(SecOrgForParameter secOrgForParameter) {
        return secOrgMapper.getQueryAll(secOrgForParameter);
    }

    @Override
    public int findOrgNameCheckedIsExist(String orgName, String orgTopId) {
        return secOrgMapper.findOrgNameCheckedIsExist(orgName,orgTopId);
    }

    @Override
    public List<SecMenu> findOrgAllotMenuList(SecMenu secMenu) {
        return secOrgMapper.findOrgAllotMenuList(secMenu);
    }

    @Override
    public List<SecOrg> orgTree() {
        return secOrgMapper.orgTree();
    }

}
