package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.GaugeScene;

public interface GaugeSceneMapper extends Mapper<GaugeScene> {
    GaugeScene deleteGaugeScene(GaugeScene scene);
}