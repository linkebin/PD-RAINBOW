package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecOrg;
import com.yidusoft.project.system.domain.SecOrgForParameter;
import com.yidusoft.utils.TreeNode;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
public interface SecOrgService extends Service<SecOrg> {

    /**
     * 根据组织id获取该组织的全部父id,以逗号分割
     * @param orgId
     * @return
     */
    String findOrgParentStringIds(String orgId);

    List<TreeNode> getTree(String userId);

    List<SecOrg> getSecOrgList(SecOrg secOrg);
    List<SecOrg>  orgTree();

    List<SecOrg> findOrgChildren(String orgId);
    int getQueryAllTotal(SecOrgForParameter secOrg);
    List<SecOrg> getQueryAll(SecOrgForParameter secOrg);

    /**
     * 验证某一公司组织下的组织名称是否重复
     * @param orgName
     * @param orgTopId 公司id
     * @return
     */
    int findOrgNameCheckedIsExist(String orgName, String orgTopId);

    /**
     * 后台管理查询组织已经分配的菜单
     * @param secOrg
     * @return
     */
    List<SecMenu>  findOrgAllotMenuList(SecMenu secMenu);
}
