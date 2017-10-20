package com.yidusoft.project.questionnaire.service;
import com.yidusoft.core.Result;
import com.yidusoft.project.questionnaire.domain.Gauge;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface GaugeService extends Service<Gauge> {
    //分页条件查询量表
    List<Gauge> gaugeListByPage(Gauge gauge);
    //添加量表
    Result addGauge(Gauge gauge, String questionStr,String tagId,String sceneId);
    //修改量表
    Result updateGauge(Gauge gauge, String questionStr);

    //查询所有的量表
    List<Gauge> findGaugeAll();
    //查询量表 相关的 标签   场景
    Result  getGaugeInfo(String id);
}
