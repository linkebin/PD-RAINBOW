package com.yidusoft.project.transaction.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.transaction.dao.UserQuestionnairesMapper;
import com.yidusoft.project.transaction.domain.UserQuestionnaires;
import com.yidusoft.project.transaction.service.AccountInfoService;
import com.yidusoft.project.transaction.service.UserQuestionnairesService;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class UserQuestionnairesServiceImpl extends AbstractService<UserQuestionnaires> implements UserQuestionnairesService {
    @Resource
    private UserQuestionnairesMapper userQuestionnairesMapper;

    @Resource
    private UserQuestionnairesService userQuestionnairesService;

    @Resource
    private AccountInfoService accountInfoService;

    //判断余额是否不等于0
    @Override
    public boolean flgBalance() {
        boolean state=false;
        UserQuestionnaires userQuestionnaires= userQuestionnairesMapper.flgBalance(Security.getUserId());
        int  total=userQuestionnaires.getQuestionnairesTotal();
        if(total>0){
            state=true;
        }
        return state;
    }
    //消费扣除问卷
    @Override
    public void deduction() {
        UserQuestionnaires userQuestionnaires= userQuestionnairesMapper.flgBalance(Security.getUserId());
        userQuestionnaires.setQuestionnairesTotal(userQuestionnaires.getQuestionnairesTotal()-1);
        userQuestionnaires.setQuestionnairesCumulativeTotal(userQuestionnaires.getQuestionnairesCumulativeTotal()+1);
        this.update(userQuestionnaires);
    }

}
