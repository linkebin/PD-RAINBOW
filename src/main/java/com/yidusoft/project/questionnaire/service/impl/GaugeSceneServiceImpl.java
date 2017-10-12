package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.GaugeSceneMapper;
import com.yidusoft.project.questionnaire.domain.GaugeScene;
import com.yidusoft.project.questionnaire.service.GaugeSceneService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class GaugeSceneServiceImpl extends AbstractService<GaugeScene> implements GaugeSceneService {
    @Resource
    private GaugeSceneMapper gaugeSceneMapper;

}
