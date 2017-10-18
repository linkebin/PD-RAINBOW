package com.yidusoft.project.questionnaire.service;
import com.yidusoft.core.Result;
import com.yidusoft.project.questionnaire.domain.GaugeTagMiddle;
import com.yidusoft.core.Service;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface GaugeTagMiddleService extends Service<GaugeTagMiddle> {

    //添加量表 与  标签的id
     Result addGaugeTagMiddle(String ids,String gaugeId);

    //删除标签
    Result deleteGaugeTagMiddle(GaugeTagMiddle gaugeTagMiddle);
}
