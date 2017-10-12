package com.yidusoft.project.channel.service.impl;

import com.yidusoft.project.channel.dao.ChannelManageMapper;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class ChannelManageServiceImpl extends AbstractService<ChannelManage> implements ChannelManageService {
    @Resource
    private ChannelManageMapper channelManageMapper;

}
