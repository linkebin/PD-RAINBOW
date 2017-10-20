package com.yidusoft.project.business.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.business.domain.VisitorRegister;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface VisitorRegisterService extends Service<VisitorRegister> {

    /**
     * 根据咨询师id查询所对应的客户
     * @param visitorRegister
     * @return
     */
    List<VisitorRegister> findViitorByCounselorId(VisitorRegister visitorRegister);

}
