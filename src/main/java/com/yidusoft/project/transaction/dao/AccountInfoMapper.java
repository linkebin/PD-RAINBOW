package com.yidusoft.project.transaction.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.transaction.domain.AccountInfo;

import java.util.List;
import java.util.Map;

public interface AccountInfoMapper extends Mapper<AccountInfo> {

    List<AccountInfo> getAccountByTime(Map map);
}