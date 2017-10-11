package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuForParameter;
import com.yidusoft.utils.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SecMenuMapper extends Mapper<SecMenu> {

     /**
      * 根据角色获取菜单列表
      * @param roleId
      * @return
      */
     List<SecMenu> menuListByRoleId(String roleId);

     /**
      *查询菜单表全部数据
      * @return
      */
     List<TreeNode> menuTree();

     /**
      * 根据用户查询菜单
      * @param userId
      * @return
      */
     List<SecMenu> loadUserMenu(String userId);

     /**
      *查询菜单表全部数据
      * @return
      */
     List<SecMenu> queryAll();

     /**
      *根据用户查询用户拥有的菜单
      * @param userId
      * @return
      */
     List<SecMenu> indexMenuTree(@Param("userId") String userId);

     /**
      * 后台管理 菜单管理获取菜单列表
      * @param secMenu
      * @return
      */
     List<SecMenu> getMenu(SecMenu secMenu);

     //查询可用 没被删除的菜单
     List<SecMenu> getMenuForAvailable();
     //查询用户菜单
     List<SecMenu> menuToTree(String id);

     /**
      *根据用户查询菜单
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


     List<Integer> querySort(String parentId);

     List<String> getChildrenById(String id);

     List<String> findByMenuName(String menuName);

     /**
      * 根据菜单类型查询出所有菜单
      * @return
      */
     List<SecMenu> findMenuAll(String menuType);

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


     /**
      * 获取某个菜单的下一级的数量，用于显示排序
      * @param menuId
      * @return
      */
     int findMenuSortCountByParentId(String menuId);
}