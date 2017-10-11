package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecMenuRole;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.utils.TreeNode;

import java.util.List;

public interface SecRoleMapper extends Mapper<SecRole> {
    List<SecRole> roleListByUserId(String userId);
    /**
     * 给角色分配权限
     * @param secMenuRole
     * @return
     */
    void addMenuRole(SecMenuRole secMenuRole);

    List<SecRole> getAll(SecRole secRole);

    void deleteList(String id);

    List<SecRole> getRole();

    /**
     * 根据用户ID 查询用户角色
     * @param secId
     * @return
     */
    List<SecRole> getSecUserRoles(String secId);

    /**
     * 查询角色是否存在
     * @param roleName
     * @return
     */
    List<String> findByRoleName(String roleName);


    /**
     * 根据公司id获取该公司下的角色列表
     * @param secRole
     * @return
     */
    List<SecRole> findRoleByCompanyId(SecRole secRole);

    //成员树
    List<TreeNode> userTreeList();
}