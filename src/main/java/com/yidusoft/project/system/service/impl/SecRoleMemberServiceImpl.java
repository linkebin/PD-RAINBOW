package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SecRoleMemberMapper;
import com.yidusoft.project.system.domain.SecRoleMember;
import com.yidusoft.project.system.service.SecRoleMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/07/16.
 */
@Service
@Transactional
public class SecRoleMemberServiceImpl extends AbstractService<SecRoleMember> implements SecRoleMemberService {
    @Resource
    private SecRoleMemberMapper secRoleMemberMapper;

    @Override
    public void  deleteUserIdAndRoleId(SecRoleMember secRoleMember){
        secRoleMemberMapper.deleteUserIdAndRoleId(secRoleMember);
    };

    @Override
    public void  deleteByRoleId(String roleId){
        secRoleMemberMapper.deleteByRoleId(roleId);
    }

    @Override
    public List<String> getMemberAll(Map map){ return secRoleMemberMapper.getMemberAll(map); }

    @Override
    public int findRoleMemberIsExits(SecRoleMember secRoleMember) {
        return secRoleMemberMapper.findRoleMemberIsExits(secRoleMember);
    }

}
