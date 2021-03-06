package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.utils.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SecUserMapper extends Mapper<SecUser> {

    /**
     * 根据邀请码查询
     * @param inviterCode
     * @return
     */
    SecUser findSecUserByInviterCode(String inviterCode);

    /**
     * 根据多个用户id查询用户部门信息
     * @param ids
     * @return
     */
    List<SecUser> findSecUserOrgInfos(@Param("ids") List<String> ids);

    List<SecUser> userListByRoleId(String roleId);

    /**
     * 获取用户信息包含部门信息
     * @param id
     * @return
     */
    SecUser findUserInfoAndOrgInfoById(String id);

    //通过账号查询用户的信息
    SecUser getSecUserInfo(String account);

    SecUser isSecUser(String account);

    int  isSecUser1(String account);

    //通过角色id查询角色成员
    List<SecUser> roleUserName(String id);
    //通过角色id查询出添加或未添加的成员
    List roleChooseUser(Map map);

    List<SecUser> getQueryAll(SecUser secUser);



    /**
     * 查询出用户没有拥有的角色列表
     * @param  已拥有角色的ID数组
     * @return
     */
    List<SecRole> getUserNoRoleList(String userId);

    int getQueryAllTotal(SecUser secUser);

    /**
     * 获取用户树
     * @param id
     * @return
     */
    List<TreeNode> userTreeList(String id);



    /**
     * 获取用户树
     * @param
     * @return
     */
    List<TreeNode> leadingTreeList(SecUser secUser);

    /**
     * 根据组织查询用户集合
     * @param orgId
     * @return
     */
    List<SecUser> findOrgUserList(String orgId);

    List<SecUser> findOrgByUsers(SecUser secUser);

    /**
     * 查询渠道商账号
     * @param channelId
     * @return
     */
    SecUser findChannelDefaultAccount(String channelId);

    /**
     * 查询每月客户增长数量
     * @return
     */
    List<Map<String,Object>> findPerMonthCustomerCount();

    /**
     * 查询每个渠道商邀请的用户的数量
     * @return
     */
    List<Map<String,Object>> findPerChannelCount();

    List<Map<String,Object>> findAllVisitorCount(String userId);

    List<Map<String,Object>> findAreaVisitor(String userId);

    //所有客户
    List<Map<String,Object>> userListForCustomer(Map map);

    //客户区域树
    List<Map<String,Object>> customerAreaTree();

}