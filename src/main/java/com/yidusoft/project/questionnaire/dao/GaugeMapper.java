package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.Gauge;

import java.util.List;

public interface GaugeMapper extends Mapper<Gauge> {
   //分页条件查询量表
    List<Gauge> gaugeListByPage(Gauge gauge);
}