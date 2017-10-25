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
}
