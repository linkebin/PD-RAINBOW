package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.GaugeQuestionFactor;

import java.util.List;

public interface GaugeQuestionFactorMapper extends Mapper<GaugeQuestionFactor> {

    //查询量表相关的问题
  List<GaugeQuestionFactor> findGaugeQuestionFactor(String id);
}