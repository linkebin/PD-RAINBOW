package com.yidusoft.project.transaction.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.transaction.domain.UserQuestionnaires;

public interface UserQuestionnairesMapper extends Mapper<UserQuestionnaires> {
    //判断余额是否不等于0
    UserQuestionnaires  flgBalance(String userId);
}