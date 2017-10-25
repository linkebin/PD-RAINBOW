package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;

import java.util.List;

public interface DataAcquisitionMapper extends Mapper<DataAcquisition> {

    //首页查询问卷使用信息
    List<DataAcquisition> questionnaireUseInfoListByPage();
}