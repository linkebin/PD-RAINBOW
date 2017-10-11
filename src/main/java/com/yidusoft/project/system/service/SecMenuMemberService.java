package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuMember;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
public interface SecMenuMemberService extends Service<SecMenuMember> {
    /**
     * 更新用户的权限
     * @param secMenuMember
     * @return
     */
    void addMenuMember(SecMenuMember secMenuMember);

    /**
     * 查询用户拥有的菜单权限
     * @param secId
     * @return
     */
    List<SecMenuMember> findSecMenu(String secId);

    /**
     * 删除用户的权限
     * @param secMenuMember
     * @return
     */
    void deleteMenuMember(SecMenuMember secMenuMember);

    /**
     * 查询某个用户的权限
     * @param userId
     * @return
     */
    List<SecMenu> findMenuByUser(String userId);

    /**
     * 查询某个用户全部角色拥有的菜单权限
     * @param userId
     * @return
     */
    List<SecMenu> findMenuByUserRole(String userId);

    /**
     * 查询用户拥有的菜单权限
     * @param secId
     * @return
     */
    List<SecMenuMember> findUserMenu(String secId);
    void deleteByUserIdAll(String userId);

}
