package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SysMessage;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/08/23.
 */
public interface SysMessageService extends Service<SysMessage> {
    int msgCount(String userId);

    List<SysMessage> findUserMessagesList(String userId);
}
