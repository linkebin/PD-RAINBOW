package com.yidusoft.project.channel.service.impl;

import com.yidusoft.project.channel.dao.ChannelRuleMapper;
import com.yidusoft.project.channel.domain.ChannelRule;
import com.yidusoft.project.channel.service.ChannelRuleService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class ChannelRuleServiceImpl extends AbstractService<ChannelRule> implements ChannelRuleService {
    @Resource
    private ChannelRuleMapper channelRuleMapper;

    @Override
    public List<Map<String, Object>> findChannelRuleTimeClashList(Map<String, Object> map) {
        return channelRuleMapper.findChannelRuleTimeClashList(map);
    }

    @Override
    public List<Map<String, Object>> findChannelByRuleIdList(Map<String, Object> map) {
        return channelRuleMapper.findChannelByRuleIdList(map);
    }

    @Override
    public void deleteRuleChannelAll(String ruleId) {
        channelRuleMapper.deleteRuleChannelAll(ruleId);
    }
}
