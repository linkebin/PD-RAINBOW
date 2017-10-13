package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.GaugeMapper;
import com.yidusoft.project.questionnaire.domain.Gauge;
import com.yidusoft.project.questionnaire.service.GaugeService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class GaugeServiceImpl extends AbstractService<Gauge> implements GaugeService {
    @Resource
    private GaugeMapper gaugeMapper;

    //分页条件查询量表
    @Override
    public List<Gauge> gaugeListByPage(Gauge gauge) {
        return gaugeMapper.gaugeListByPage(gauge);
    }
}
