package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SysMessageMapper;
import com.yidusoft.project.system.domain.SysMessage;
import com.yidusoft.project.system.service.SysMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/08/23.
 */
@Service
@Transactional
public class SysMessageServiceImpl extends AbstractService<SysMessage> implements SysMessageService {
    @Resource
    private SysMessageMapper sysMessageMapper;

    /**
     * 查询未读消息
     * @param userId
     * @return
     */
    @Override
    public int msgCount(String userId) {
        SysMessage sysMessage = new SysMessage();
        sysMessage.setRecid(userId);
        sysMessage.setStatus(0);
        sysMessage.setDeleted(0);
        return sysMessageMapper.selectCount(sysMessage);
    }

    @Override
    public List<SysMessage> findUserMessagesList(String userId) {
        return sysMessageMapper.findUserMessagesList(userId);
    }
}
