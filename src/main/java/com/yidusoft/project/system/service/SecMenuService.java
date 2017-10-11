package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuForParameter;
import com.yidusoft.utils.IndexTreeNode;
import com.yidusoft.utils.TreeNode;

import java.util.HashMap;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
public interface SecMenuService extends Service<SecMenu> {

    /**
     * 获取某个菜单的下一级的数量，用于显示排序
     * @param menuId
     * @return
     */
    int findMenuSortCountByParentId(String menuId);

    List<SecMenu> menuListByRoleId(String roleId);
    List<TreeNode> menuTree();
    //查询用户的权限菜单
    List<SecMenu> menuToTree(String id);
    List<SecMenu> queryAll();
    List<IndexTreeNode> indexMenuTree(String userId, String orgId);
    List<SecMenu> loadUserMenu(String userId);//加载用户菜单

    /**
     * 获取全部菜单 分页
     * @param secMenu
     * @return
     */
    List<SecMenu> getMenu(SecMenu secMenu);
    //查询可用 没被删除的菜单
    List<SecMenu> getMenuForAvailable();

    /**
     * 删除排序
     * @param menuId
     */
    void deleteSort(String menuId);

    /**
     * 增加排序
     * @param parentId
     * @param sort
     */
    void addSort(String parentId, int sort);

    /**
     * 修改排序
     * @param menuId
     * @param newSort
     */
    void updateSort(String menuId, int newSort);

    /**
     * 查出所有子菜单的排序
     * @param parentId
     */
    List<Integer> querySort(String parentId);

    /**
     * 查询菜单下有没有子目录
     * @param id
     * @return
     */
    List<String> getChildrenById(String id);

    /**
     * 查询菜单是否已存在
     * @param menuName
     * @return
     */
    List<String> findByMenuName(String menuName);

    /**
     * 后台管理查询出所以菜单
     * @return
     */
    List<SecMenu> findMenuAll(String menuType);
/*------------------一道华丽的分割线--------------------------------*/
    //树结构
    List<TreeNode> getTree(String userId);
    //分页
    int getQueryAllTotal(SecMenuForParameter secMenuForParameter);
    List<SecMenu> getQueryAll(SecMenuForParameter secMenuForParameter);
    //查询排序
    List<Integer> webQuerySort(HashMap map);


    /**
     * 根据角色id查询角色的权限树
     * @param roleId
     * @return
     */
    List<SecMenu> findCompanyRoleMenuByRoleId(String roleId);
}
