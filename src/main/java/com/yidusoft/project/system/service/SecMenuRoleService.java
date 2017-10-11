package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuRole;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
public interface SecMenuRoleService extends Service<SecMenuRole> {
    /**
     * 更新角色的权限
     * @param secMenuRole
     */
    void addMenuRole(SecMenuRole secMenuRole);

    /**
     * 删除角色的权限
     * @param secMenuRole
     */
    void deleteMenuRole(SecMenuRole secMenuRole);

    /**
     * 查询某个角色的权限
     * @param roleId
     */
    List<SecMenu> findByRole(String roleId);
    List<SecMenu> findByRole(SecMenuRole secMenuRole);

    /**
     * 查询用户全部角色的权限
     * @param secId
     * @return
     */
    List<SecMenuRole> findSecRoleMenu(String secId);

    /**
     * 查询角色的全部权限
     * @param secId
     * @return
     */
    List<SecMenuRole> findRoleMenu(String secId);

    /**
     * 根据角色id删除角色的菜单权限
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}
