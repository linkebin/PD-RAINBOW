package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SysMessage;

import java.util.List;

public interface SysMessageMapper extends Mapper<SysMessage> {

    List<SysMessage> findUserMessagesList(String userId);
}