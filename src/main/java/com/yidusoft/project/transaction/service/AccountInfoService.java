package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.AccountInfo;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/11/17.
 */
public interface AccountInfoService extends Service<AccountInfo> {
    List<AccountInfo> getAccountByTime(Map map);
}

