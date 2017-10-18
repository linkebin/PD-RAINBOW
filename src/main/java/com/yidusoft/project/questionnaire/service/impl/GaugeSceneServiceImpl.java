package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.dao.GaugeSceneMapper;
import com.yidusoft.project.questionnaire.domain.GaugeScene;
import com.yidusoft.project.questionnaire.service.GaugeSceneService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class GaugeSceneServiceImpl extends AbstractService<GaugeScene> implements GaugeSceneService {
    @Resource
    private GaugeSceneMapper gaugeSceneMapper;

    @Override
    public Result addGaugeScene(String ids, String gaugeId) {
        try{
            if(!"".equals(ids)){
                String [] idArray=ids.split(",");
                for(int i=0;i<idArray.length;i++){
                    if(!"".equals(idArray[i])){
                        GaugeScene gaugeScene=new GaugeScene();
                        gaugeScene.setId(UUID.randomUUID().toString());
                        gaugeScene.setGaugeId(gaugeId);
                        gaugeScene.setSceneId(idArray[i]);
                        gaugeSceneMapper.insert(gaugeScene);
                    }
                }
            }

        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }


        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result deleteGaugeScene(GaugeScene gaugeScene) {
        try{
               GaugeScene gaugeScenes=gaugeSceneMapper.deleteGaugeScene(gaugeScene);
               gaugeSceneMapper.delete(gaugeScenes);
        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }


        return ResultGenerator.genSuccessResult();
    }
}
