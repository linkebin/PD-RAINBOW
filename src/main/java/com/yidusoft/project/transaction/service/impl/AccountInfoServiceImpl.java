package com.yidusoft.project.transaction.service.impl;

import com.yidusoft.project.transaction.dao.AccountInfoMapper;
import com.yidusoft.project.transaction.domain.AccountInfo;
import com.yidusoft.project.transaction.service.AccountInfoService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/11/17.
 */
@Service
@Transactional
public class AccountInfoServiceImpl extends AbstractService<AccountInfo> implements AccountInfoService {
    @Resource
    private AccountInfoMapper accountInfoMapper;

    @Override
    public List<AccountInfo> getAccountByTime(Map map) {
        return accountInfoMapper.getAccountByTime(map);
    }
}
