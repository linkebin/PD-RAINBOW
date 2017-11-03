package com.yidusoft.project.questionnaire.service;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface DataAcquisitionService extends Service<DataAcquisition> {

    //首页查询问卷使用信息
    List<DataAcquisition> questionnaireUseInfoListByPage();
    // 症状自评量表-SCL90  的规则结论
    String  symptomConclusion(String acquisitionId,String userId);
    //抑郁自评 的规则结论
    String  depressedOrAnxiousConclusion(String acquisitionId,String userId,String type);
    //根据日期查询来访者使用的问卷
    List<DataAcquisition> findQuestionnaireForVisitor (DataAcquisition dataAcquisition);

    List<DataAcquisition> findMyQuestionnaireListByPage(DataAcquisition dataAcquisition);
}
