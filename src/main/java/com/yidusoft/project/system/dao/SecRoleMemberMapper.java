package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecRoleMember;

import java.util.List;
import java.util.Map;

public interface SecRoleMemberMapper extends Mapper<SecRoleMember> {
    //通过角色id和成员id删除
    void deleteUserIdAndRoleId(SecRoleMember secRoleMember);

    //通过角色id删除
    void  deleteByRoleId(String roleId);

    //通过成员id查询
    List<String> getMemberAll(Map map);

    /**
     * 查询角色成员是否已经存在
     * @param
     * @param
     * @return
     */
    int findRoleMemberIsExits(SecRoleMember secRoleMember);
}