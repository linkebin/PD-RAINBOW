package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecMenuRole;

import java.util.List;

public interface SecMenuRoleMapper extends Mapper<SecMenuRole> {

    List<SecMenuRole> findSecRoleMenu(String secId);

    List<SecMenuRole> findRoleMenu(String secId);

    void deleteByRoleId(String roleId);
}