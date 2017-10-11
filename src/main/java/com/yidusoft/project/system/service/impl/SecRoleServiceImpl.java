package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SecRoleMapper;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.project.system.service.SecRoleService;
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
public class SecRoleServiceImpl extends AbstractService<SecRole> implements SecRoleService {
    @Resource
    private SecRoleMapper secRoleMapper;
    @Override
    public List<SecRole> roleListByUserId(String userId) {
        return secRoleMapper.roleListByUserId(userId);
    }

    @Override
    public List<SecRole> getAll(SecRole secRole){ return secRoleMapper.getAll(secRole); };

    @Override
    public void deleteList(String id){  secRoleMapper.deleteList(id); }

    @Override
    public List<SecRole> getRole(){ return secRoleMapper.getRole(); }

    @Override
    public List<SecRole> getSecUserRoles(String secId) {
        return secRoleMapper.getSecUserRoles(secId);
    }

    @Override
    public List<String> findByRoleName(String roleName) {
        return secRoleMapper.findByRoleName(roleName);
    }

    @Override
    public List<SecRole> findRoleByCompanyId(SecRole secRole) {
        return secRoleMapper.findRoleByCompanyId(secRole);
    }

    @Override
    public List<TreeNode> userTreeList() {
        return secRoleMapper.userTreeList();
    }
}
