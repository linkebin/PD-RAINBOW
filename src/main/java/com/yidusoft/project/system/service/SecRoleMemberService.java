package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecRoleMember;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
public interface SecRoleMemberService extends Service<SecRoleMember> {
    /**
     * 根据角色id和成员id进行删除
     * @param secRoleMember
     */
    void deleteUserIdAndRoleId(SecRoleMember secRoleMember);

    /**
     * 根据角色id进行删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);

    /**
     * 查询未添加的成员
     * @param map
     */
    List<String> getMemberAll(Map map);

    /**
     * 查询角色成员是否已经存在
     * @param userId
     * @param roleId
     * @return
     */
    int findRoleMemberIsExits(SecRoleMember secRoleMember);
    ;
}
