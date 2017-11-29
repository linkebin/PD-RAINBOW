package com.yidusoft.project.business.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.business.domain.VisitorRegister;

import java.util.List;
import java.util.Map;

public interface VisitorRegisterMapper extends Mapper<VisitorRegister> {

    /**
     * 根据咨询师id查询所对应的客户
     * @param visitorRegister
     * @return
     */
    List<VisitorRegister> findViitorByCounselorId(VisitorRegister visitorRegister);

    /**
     * 根据咨询师id查询所对应的客户,按最近时间排序
     * @param visitorRegister
     * @return
     */
    List<Map<String,Object>> findViitorByCounselorIdSortTime(VisitorRegister visitorRegister);

    /**
     * 根据创建人id获取其客户的信息
     * @param creator
     * @return
     */
    List<VisitorRegister> findVitorByCreator(String creator);
}