package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.UserQuestionnaires;

import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface UserQuestionnairesService extends Service<UserQuestionnaires> {

    //判断余额是否不等于0
      boolean   flgBalance();
    //消费扣除问卷
      void   deduction();

    //账户会员信息
    Map getVipInfo();
}
