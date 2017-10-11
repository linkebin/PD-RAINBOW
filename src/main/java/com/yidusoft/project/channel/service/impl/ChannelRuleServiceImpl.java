package com.yidusoft.project.channel.service.impl;

import com.yidusoft.project.channel.dao.ChannelRuleMapper;
import com.yidusoft.project.channel.domain.ChannelRule;
import com.yidusoft.project.channel.service.ChannelRuleService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class ChannelRuleServiceImpl extends AbstractService<ChannelRule> implements ChannelRuleService {
    @Resource
    private ChannelRuleMapper channelRuleMapper;

}
