package com.yidusoft.project.channel.service;
import com.yidusoft.project.channel.domain.AccountRule;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface AccountRuleService extends Service<AccountRule> {

    /**
     * 根据参数查询渠道规则列表
     * @param accountRule
     * @return
     */
    List<AccountRule> finndAccountRuleByParameterList(AccountRule accountRule);

}
