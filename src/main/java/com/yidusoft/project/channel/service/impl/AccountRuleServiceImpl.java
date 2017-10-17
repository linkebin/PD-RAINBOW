package com.yidusoft.project.channel.service.impl;

import com.yidusoft.project.channel.dao.AccountRuleMapper;
import com.yidusoft.project.channel.domain.AccountRule;
import com.yidusoft.project.channel.service.AccountRuleService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class AccountRuleServiceImpl extends AbstractService<AccountRule> implements AccountRuleService {
    @Resource
    private AccountRuleMapper accountRuleMapper;

    @Override
    public List<AccountRule> finndAccountRuleByParameterList(AccountRule accountRule) {
        return accountRuleMapper.finndAccountRuleByParameterList(accountRule);
    }
}
