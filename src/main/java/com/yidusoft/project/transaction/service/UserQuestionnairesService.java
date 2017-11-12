package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.UserQuestionnaires;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface UserQuestionnairesService extends Service<UserQuestionnaires> {

    //判断余额是否不等于0
      boolean   flgBalance();
    //消费扣除问卷
      void   deduction();
    //消费扣除活动发起人的使用卷
    void deleteDuction(String activityId);
}
