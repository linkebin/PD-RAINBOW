package com.yidusoft.project.channel.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.channel.domain.ChannelRule;

import java.util.List;
import java.util.Map;

public interface ChannelRuleMapper extends Mapper<ChannelRule> {

    List<Map<String,Object>> findChannelRuleTimeClashList(Map<String,Object> map);

    //根据规则获取渠道
    List<Map<String,Object>> findChannelByRuleIdList(Map<String,Object> map);

    void deleteRuleChannelAll(String ruleId);

    void deletedOne(Map<String,Object> map);

    //根据渠道获取结算规则
    List<Map<String,Object>> findRuleByChannel(Map<String,Object> map);
}