package com.yidusoft.project.channel.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.channel.domain.AccountRule;

import java.util.List;

public interface AccountRuleMapper extends Mapper<AccountRule> {

    /**
     * 根据参数查询渠道规则列表
     * @param accountRule
     * @return
     */
    List<AccountRule> finndAccountRuleByParameterList(AccountRule accountRule);
}