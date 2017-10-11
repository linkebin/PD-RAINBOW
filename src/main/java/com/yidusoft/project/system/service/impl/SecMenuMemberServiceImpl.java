package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SecMenuMapper;
import com.yidusoft.project.system.dao.SecMenuMemberMapper;
import com.yidusoft.project.system.dao.SecMenuRoleMapper;
import com.yidusoft.project.system.dao.SecRoleMemberMapper;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuMember;
import com.yidusoft.project.system.service.SecMenuMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
@Service
@Transactional
public class SecMenuMemberServiceImpl extends AbstractService<SecMenuMember> implements SecMenuMemberService {
    @Resource
    private SecMenuMemberMapper secMenuMemberMapper;

    @Resource
    private SecMenuMapper secMenuMapper;

    @Resource
    private SecRoleMemberMapper secRoleMemberMapper;

    @Resource
    private SecMenuRoleMapper secMenuRoleMapper;

    /**
     * 更新用户的权限
     * @param secMenuMember
     * @return
     */
    public void addMenuMember(SecMenuMember secMenuMember){
        //删除该用户所有权限
        Example example = new Example(SecMenuMember.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",secMenuMember.getUserId());
        secMenuMemberMapper.deleteByCondition(example);//根据指定的条件集合进行删除
        //重新分配权限
        if(!StringUtils.isEmpty(secMenuMember.getMenuId())){
            String[] menuArr = secMenuMember.getMenuId().split(",");
            for(String menuId : menuArr ){
                SecMenuMember member = new SecMenuMember();
                member.setUserId(secMenuMember.getUserId());
                member.setMenuId(menuId);
                member.setId(UUID.randomUUID().toString());
                secMenuMemberMapper.insert(member);
            }
        }
    }

    /**
     * 删除用户的权限
     * @param secMenuMember
     * @return
     */
    public void deleteMenuMember(SecMenuMember secMenuMember){
        //删除该用户指定的权限
        if(!StringUtils.isEmpty(secMenuMember.getMenuId())){
            String[] menuArr = secMenuMember.getMenuId().split(",");
            for(String menuId : menuArr ){
                Example example = new Example(SecMenuMember.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("userId",secMenuMember.getUserId());
                criteria.andEqualTo("menuId",menuId);
                secMenuMemberMapper.deleteByCondition(example);//根据指定的条件集合进行删除
            }
        }
    }

    /**
     * 查询某个用户的权限
     * @param userId
     * @return
     */
    @Override
    public List<SecMenu> findMenuByUser(String userId) {
        Set<SecMenu> secMenus = new HashSet<>();
        List<SecMenu> menuList = secMenuMapper.findMenuByUser(userId);
        return menuList;
    }

    @Override
    public List<SecMenu> findMenuByUserRole(String userId) {
        return secMenuMapper.findMenuByUserRole(userId);
    }

    @Override
    public void deleteByUserIdAll(String userId) {
        secMenuMemberMapper.deleteByUserIdAll(userId);
    }

    @Override
    public  List<SecMenuMember> findSecMenu(String secId){

        return secMenuMemberMapper.findSecMenu(secId);
    }

    @Override
    public  List<SecMenuMember> findUserMenu(String secId){

        return secMenuMemberMapper.findUserMenu(secId);
    }

}
