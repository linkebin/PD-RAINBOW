package com.yidusoft.project.questionnaire.service;
import com.yidusoft.project.questionnaire.domain.GaugeQuestionFactor;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestionFactor;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnaireQuestionFactorService extends Service<QuestionnaireQuestionFactor> {
    //查询问卷相关的问题因子
    List<QuestionnaireQuestionFactor> findQuestionnaireQuestionFactor(String id);
}
