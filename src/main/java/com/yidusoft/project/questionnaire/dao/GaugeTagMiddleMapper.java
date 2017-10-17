package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.core.Result;
import com.yidusoft.project.questionnaire.domain.GaugeTagMiddle;

import java.util.List;

public interface GaugeTagMiddleMapper extends Mapper<GaugeTagMiddle> {
    //删除标签
    GaugeTagMiddle deleteGaugeTagMiddle(GaugeTagMiddle gaugeTagMiddle);
}