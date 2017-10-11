package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.utils.TreeNode;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
public interface SecRoleService extends Service<SecRole> {
    List<SecRole> roleListByUserId(String userId);

    /**
     * 模糊查询
     * @param secRole
     * @return
     */
    List<SecRole> getAll(SecRole secRole);

    /**
     * 根据id删除
     * @param id
     */
    void deleteList(String id);

    /**
     * 查询出所有角色
     * @return
     */
    List<SecRole> getRole();

    /**
     * 根据角色id获取到成员
     * @param secId
     * @return
     */
    List<SecRole> getSecUserRoles(String secId);

    /**
     * 查询角色是否存在
     * @param roleName
     * @return
     */
    List<String>  findByRoleName(String roleName);

    /**
     * 根据公司id获取该公司下的角色列表
     * @param secRole
     * @return
     */
    List<SecRole> findRoleByCompanyId(SecRole secRole);
    //成员树
    List<TreeNode> userTreeList();
}
