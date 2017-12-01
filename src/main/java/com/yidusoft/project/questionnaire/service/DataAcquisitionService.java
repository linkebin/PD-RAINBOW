package com.yidusoft.project.questionnaire.service;

import com.yidusoft.core.Result;
import com.yidusoft.core.Service;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface DataAcquisitionService extends Service<DataAcquisition> {

    //首页查询问卷使用信息
    List<DataAcquisition> questionnaireUseInfoListByPage();
    //根据日期查询来访者使用的问卷
    List<DataAcquisition> findQuestionnaireForVisitor (DataAcquisition dataAcquisition);
    //查询活动的填报人数
    Integer findCountByActivityId(String activityId);
    List<DataAcquisition> findMyQuestionnaireListByPage(Map<String,Object> map);
    //查询来访者填写问卷的总数
    int  getDataAcquisitionTotal(String userId);
    //填写问卷类别总数
    List<DataAcquisition> getQuestionnaireTypeTotal(String userId);
    //查询来访者的填写结果
    List<DataAcquisition> findDataAcquistionForVisitor(DataAcquisition dataAcquisition);
    //来访者的问卷统计分析
    public Result getDataAcquisitionForVisitingCount(String userId);
}
