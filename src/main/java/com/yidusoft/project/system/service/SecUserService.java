package com.yidusoft.project.system.service;

import com.yidusoft.core.Result;
import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.utils.TreeNode;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
public interface SecUserService extends Service<SecUser> {





    SecUser findUserInfoAndOrgInfoById(String id);
    /**
     * 根据多个用户id查询用户部门信息
     * @param ids
     * @return
     */
    List<SecUser> findSecUserOrgInfos(List<String> ids);

    List<SecUser> userListByRoleId(String roleId);
    //通过账号查询用户的信息
    SecUser getSecUserInfo(String account);

    SecUser isSecUser(String account);

    int  isSecUser1(String account);

    //通过角色id查询角色成员
    List<SecUser> roleUserName(String id);

    //通过角色id查询出添加或未添加的成员
    List roleChooseUser(Map map);

    List<SecUser> getQueryAll(SecUser secUser);

    List<SecRole> getUserNoRoleList(String userId);

    int getQueryAllTotal(SecUser secUser);

    List<TreeNode> userTreeList(String id);

    List<SecUser> findOrgUserList(String orgId);

    int isExist(String params, String type);

    Result updateUser(String params);

    Result addUser(String params);



    /**
     * 获取用户树
     * @param id
     * @return
     */
    List<TreeNode> leadingTreeList(SecUser secUser);

    List<SecUser> findOrgByUsers(SecUser secUser);
}
