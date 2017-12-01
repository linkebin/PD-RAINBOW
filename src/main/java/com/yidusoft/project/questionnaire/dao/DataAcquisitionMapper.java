package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;

import java.util.List;
import java.util.Map;

public interface DataAcquisitionMapper extends Mapper<DataAcquisition> {

    //首页查询问卷使用信息
    List<DataAcquisition> questionnaireUseInfoListByPage(String creator);
    //根据日期查询来访者使用的问卷
    List<DataAcquisition> findQuestionnaireForVisitor (DataAcquisition dataAcquisition);
    List<DataAcquisition> findMyQuestionnaireListByPage(Map<String,Object> map);
    //查询活动的填报人数
    Integer findCountByActivityId(String activityId);
    //查询来访者填写问卷的总数
    int  getDataAcquisitionTotal(String userId);
    //填写问卷类别总数
    List<DataAcquisition> getQuestionnaireTypeTotal(String userId);
    //查询来访者的填写结果
    List<DataAcquisition>findDataAcquistionForVisitor(DataAcquisition dataAcquisition);
    //查询来访者的填写的问卷 不重复
    List<DataAcquisition>  findQuestionnaireForVisitorAll(String userId);
}