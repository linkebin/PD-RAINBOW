package com.yidusoft.project.business.service;

import com.yidusoft.core.Result;
import com.yidusoft.core.Service;
import com.yidusoft.project.business.domain.VisitorRegister;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface VisitorRegisterService extends Service<VisitorRegister> {

    //查询来访者的省份
    List<Map<String,Object>> findVisitorRegisterProvince();
    /**
     * 根据咨询师id查询所对应的客户
     * @param visitorRegister
     * @return
     */
    List<VisitorRegister> findViitorByCounselorId(VisitorRegister visitorRegister);
    //为来访者使用问卷
    Result getCheckQuestionnaireForVisitor();

    List<Map<String,Object>> findViitorByCounselorIdSortTime(VisitorRegister visitorRegister);

    /**
     * 根据创建人id获取其客户的信息
     * @param map
     * @return
     */
    List<VisitorRegister> findVitorByCreator(Map map);

    /**
     * 客户统计分析
     * @param startTime
     * @param endTime
     * @return
     */
    Result acquisitionOfStatisticalAnalysis(Date startTime, Date endTime, String sex, String maritalStatus, String belief,String ageGroupsIds,String goalIds,String types);

    /*Result getQuestion(Date startTime, Date endTime, String sex, String maritalStatus, String belief,String questionName,String type);*/
}
