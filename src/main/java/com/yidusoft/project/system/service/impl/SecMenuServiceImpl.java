package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SecMenuMapper;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuForParameter;
import com.yidusoft.project.system.service.SecMenuService;
import com.yidusoft.utils.IndexTreeBuilder;
import com.yidusoft.utils.IndexTreeNode;
import com.yidusoft.utils.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
@Service
@Transactional
public class SecMenuServiceImpl extends AbstractService<SecMenu> implements SecMenuService {
    @Resource
    private SecMenuMapper secMenuMapper;

    @Override
    public int findMenuSortCountByParentId(String menuId) {
        return secMenuMapper.findMenuSortCountByParentId(menuId);
    }

    @Override
    public List<SecMenu> menuListByRoleId(String roleId) {
        return secMenuMapper.menuListByRoleId(roleId);
    }

    @Override
    public List<TreeNode> menuTree() {
        return secMenuMapper.menuTree();
    }

    @Override
    public List<SecMenu> menuToTree(String id) {
        return secMenuMapper.menuToTree(id);
    }

    @Override
    public List<SecMenu> loadUserMenu(String userId) {
        return secMenuMapper.loadUserMenu(userId);
    }

    @Override
    public List<SecMenu> queryAll() {
        return secMenuMapper.queryAll();
    }

    @Override
    public List<SecMenu> getMenu(SecMenu secMenu) { return secMenuMapper.getMenu(secMenu); }

    @Override
    public List<SecMenu> getMenuForAvailable() {
        return secMenuMapper.getMenuForAvailable();
    }

    /**
     * 查出菜单树
     * @param userId
     * @return
     */
    @Override
    public List<IndexTreeNode> indexMenuTree(String userId, String orgId) {
        List<SecMenu> menuList = secMenuMapper.indexMenuTree(userId);
        List<IndexTreeNode> list = new ArrayList<IndexTreeNode>();
        for (SecMenu secMenu : menuList){
            IndexTreeNode treeNode = new IndexTreeNode(secMenu.getId(),secMenu.getMenuName(),secMenu.getParentId(),secMenu.getIcon(),secMenu.getUrl());
            list.add(treeNode);
        }
        List<IndexTreeNode> trees = IndexTreeBuilder.buildByRecursive(list);
        return trees;
    }

    /**
     * 删除排序
     * @param menuId
     */
    @Override
    public void deleteSort(String menuId) {
        SecMenu oldSecMenu = secMenuMapper.selectByPrimaryKey(menuId);
        String parentId = oldSecMenu.getParentId();
         int sort = oldSecMenu.getSort();

            Example example = new Example(SecMenu.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("parentId",parentId);
            criteria.andEqualTo("deleted",0);
            List<SecMenu> secMenus = secMenuMapper.selectByCondition(example);
        /*
        遍历，把大于该序号的菜单往前移
         */
            for (SecMenu secMenu : secMenus){
                if (secMenu.getSort()!=null){
                    int sort1 = secMenu.getSort();
                    if (sort1>sort){
                        secMenu.setSort(sort1-1);
                        secMenuMapper.updateByPrimaryKey(secMenu);
                    }
                }
            }

    }

    /**
     * 增加排序
     * @param parentId
     * @param sort
     */
    @Override
    public void addSort(String parentId, int sort) {
        Example example = new Example(SecMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",parentId);
        criteria.andEqualTo("deleted",0);
        List<SecMenu> secMenus = secMenuMapper.selectByCondition(example);//所有菜单

        criteria.andEqualTo("sort",sort);
        List<SecMenu> secMenuSort = secMenuMapper.selectByCondition(example);//当前序号菜单
        /*
        序号已存在
         */
        if (secMenuSort.size()>0){
             /*
            遍历，把大于或等于该序号的菜单往后移
             */
            for (SecMenu secMenu : secMenus){
                if (secMenu.getSort()!=null){
                    int sort1 = secMenu.getSort();
                    if (sort1>=sort){
                        secMenu.setSort(sort1+1);
                        secMenuMapper.updateByPrimaryKey(secMenu);
                    }
                }
            }
        }
    }

    /**
     * 修改排序
     * @param menuId
     * @param sort
     */
    @Override
    public void updateSort(String menuId, int sort) {
        SecMenu oldSecMenu = secMenuMapper.selectByPrimaryKey(menuId);
        String parentId = oldSecMenu.getParentId();
        int oldSort = oldSecMenu.getSort();
        if (oldSort>sort){//大改小：将大于或等于新序号,并且小于旧序号的往后移
            Example example = new Example(SecMenu.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("parentId",parentId);
            criteria.andEqualTo("deleted",0);
            List<SecMenu> secMenus = secMenuMapper.selectByCondition(example);
            for (SecMenu secMenu : secMenus){
                if (secMenu.getSort()!=null){
                    int sort1 = secMenu.getSort();
                    if (sort1 >= sort && sort1 < oldSort){
                        secMenu.setSort(sort1+1);
                        secMenuMapper.updateByPrimaryKey(secMenu);
                    }
                }
            }
        }else if (oldSort<sort){//小改大：将大于原序号，并且小于等于新序号的往前移
            Example example = new Example(SecMenu.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("parentId",parentId);
            criteria.andEqualTo("deleted",0);
            List<SecMenu> secMenus = secMenuMapper.selectByCondition(example);
            for (SecMenu secMenu : secMenus){
                if (secMenu.getSort()!=null){
                    int sort1 = secMenu.getSort();
                    if (sort1 > oldSort && sort1 <= sort){
                        secMenu.setSort(sort1-1);
                        secMenuMapper.updateByPrimaryKey(secMenu);
                    }
                }
            }
        }
    }

    /**
     * 查出所有子菜单的排序
     * @param parentId
     */
    @Override
    public List<Integer> querySort(String parentId) {
        List<Integer> sorts = secMenuMapper.querySort(parentId);
        return sorts;
    }


    @Override
    public List<String> getChildrenById(String id){
        return secMenuMapper.getChildrenById(id);
    };


   /* @Override
    public List<String> findByMenuName(String menuName) { return secMenuMapper.findByMenuName(menuName); }
*/
    //树结构
    @Override
    public List<TreeNode> getTree(String userId) {
        return secMenuMapper.getTree(userId);
    }
    //分页
    @Override
    public int getQueryAllTotal(SecMenuForParameter secMenuForParameter) {
        return secMenuMapper.getQueryAllTotal(secMenuForParameter);
    }

    @Override
    public List<SecMenu> getQueryAll(SecMenuForParameter secMenuForParameter) {
        return secMenuMapper.getQueryAll(secMenuForParameter);
    }

    @Override
    public List<Integer> webQuerySort(HashMap map) {

        return secMenuMapper.webQuerySort(map);
    }


    @Override
    public List<SecMenu> findCompanyRoleMenuByRoleId(String roleId) {
        return secMenuMapper.findCompanyRoleMenuByRoleId(roleId);
    }

    ;
    public List<String> findByMenuName(String menuName) { return secMenuMapper.findByMenuName(menuName); }

    @Override
    public List<SecMenu> findMenuAll(String menuType) {
        return secMenuMapper.findMenuAll(menuType);
    }

    ;
}
