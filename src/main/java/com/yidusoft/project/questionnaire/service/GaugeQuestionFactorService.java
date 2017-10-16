package com.yidusoft.project.questionnaire.service;
import com.yidusoft.project.questionnaire.domain.GaugeQuestionFactor;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface GaugeQuestionFactorService extends Service<GaugeQuestionFactor> {

    //查询量表相关的问题
    List<GaugeQuestionFactor> findGaugeQuestionFactor(String id);
}
