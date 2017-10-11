package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecMenuMember;

import java.util.List;

public interface SecMenuMemberMapper extends Mapper<SecMenuMember> {

    List<SecMenuMember> findSecMenu(String secId);

    void deleteByUserIdAll(String userId);

    List<SecMenuMember> findUserMenu(String secId);

}