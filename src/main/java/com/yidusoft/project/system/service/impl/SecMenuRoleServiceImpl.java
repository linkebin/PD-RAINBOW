package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SecMenuMapper;
import com.yidusoft.project.system.dao.SecMenuRoleMapper;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuRole;
import com.yidusoft.project.system.service.SecMenuRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
@Service
@Transactional
public class SecMenuRoleServiceImpl extends AbstractService<SecMenuRole> implements SecMenuRoleService {
    @Resource
    private SecMenuRoleMapper secMenuRoleMapper;

    @Resource
    private SecMenuMapper secMenuMapper;

    @Override
    public void deleteById(String id) {

    }

    @Override
    public SecMenuRole findById(String id) {
        return null;
    }

    /**
     * 更新角色的权限
     * @param secMenuRole
     */
    @Override
    public void addMenuRole(SecMenuRole secMenuRole) {
        //删除该角色所有权限
        Example example = new Example(SecMenuRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId",secMenuRole.getRoleId());
        secMenuRoleMapper.deleteByCondition(example);//根据指定的条件集合进行删除

        //重新分配权限
        if(!StringUtils.isEmpty(secMenuRole.getMenuId())){
            String[] menuArr = secMenuRole.getMenuId().split(",");
            for(String menuId : menuArr ){
                SecMenuRole role = new SecMenuRole();
                role.setRoleId(secMenuRole.getRoleId());
                role.setMenuId(menuId);
                role.setId(UUID.randomUUID().toString());
                secMenuRoleMapper.insert(role);
            }
        }
    }
    /**
     * 删除角色的权限
     * @param secMenuRole
     */
    @Override
    public void deleteMenuRole(SecMenuRole secMenuRole) {
        //删除该角色指定权限
        if(!StringUtils.isEmpty(secMenuRole.getRoleId())){
                Example example = new Example(SecMenuRole.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("roleId",secMenuRole.getRoleId());
                secMenuRoleMapper.deleteByCondition(example);//根据指定的条件集合进行删除
        }
    }

    /**
     * 查询某个角色的权限
     * @param roleId
     * @return
     */
    @Override
    public List<SecMenu> findByRole(String roleId) {
        Example example = new Example(SecMenuRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId",roleId);
        List<SecMenuRole> secMenuRoles = secMenuRoleMapper.selectByCondition(example);
        List<SecMenu> secMenus = new ArrayList<SecMenu>();
        if (secMenuRoles.size()>0){
            for (SecMenuRole menuRole : secMenuRoles){
                String menuId = menuRole.getMenuId();
                SecMenu secMenu = secMenuMapper.selectByPrimaryKey(menuId);
                secMenus.add(secMenu);
            }
        }
        return secMenus;
    }

    @Override
    public List<SecMenu> findByRole(SecMenuRole secMenuRole) {
        return null;
    }

    @Override
   public List<SecMenuRole> findSecRoleMenu(String secId){

       return secMenuRoleMapper.findSecRoleMenu(secId);
   }

    @Override
    public List<SecMenuRole> findRoleMenu(String secId){

        return secMenuRoleMapper.findRoleMenu(secId);
    }

    public void deleteByRoleId(String roleId){
        secMenuRoleMapper.deleteByRoleId(roleId);
    };
}
