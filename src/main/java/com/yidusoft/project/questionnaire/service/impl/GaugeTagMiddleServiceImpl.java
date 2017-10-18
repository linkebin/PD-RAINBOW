package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.dao.GaugeTagMiddleMapper;
import com.yidusoft.project.questionnaire.domain.GaugeTagMiddle;
import com.yidusoft.project.questionnaire.service.GaugeTagMiddleService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.UUDecoder;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class GaugeTagMiddleServiceImpl extends AbstractService<GaugeTagMiddle> implements GaugeTagMiddleService {
    @Resource
    private GaugeTagMiddleMapper gaugeTagMiddleMapper;

    //标签和量表的添加
    @Override
    public Result addGaugeTagMiddle(String ids,String gaugeId) {

        try{
            if(!"".equals(ids)){
                String [] idArray=ids.split(",");
                for(int i=0;i<idArray.length;i++){
                    if(!"".equals(idArray[i])){
                        GaugeTagMiddle gaugeTagMiddle=new GaugeTagMiddle();
                        gaugeTagMiddle.setId(UUID.randomUUID().toString());
                        gaugeTagMiddle.setTagId(idArray[i]);
                        gaugeTagMiddle.setGaugeId(gaugeId);
                        gaugeTagMiddleMapper.insert(gaugeTagMiddle);
                    }
                }
            }

        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }


        return ResultGenerator.genSuccessResult();
    }

    //删除量表相关的标签
    @Override
    public Result deleteGaugeTagMiddle(GaugeTagMiddle gaugeTagMiddle) {
        try{

            GaugeTagMiddle gaugeTagMiddles=gaugeTagMiddleMapper.deleteGaugeTagMiddle(gaugeTagMiddle);
             gaugeTagMiddleMapper.delete(gaugeTagMiddles);

        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }


        return ResultGenerator.genSuccessResult();
    }
}
