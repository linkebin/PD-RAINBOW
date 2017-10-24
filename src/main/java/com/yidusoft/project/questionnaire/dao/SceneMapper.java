package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.Scene;

import java.util.List;

public interface SceneMapper extends Mapper<Scene> {
    List<Scene> questionnaireSceneListByPage(Scene scene);
    //查询量表相关的场景
    List<Scene> findSceneForGauge(String gaugeId);
   //查询问卷相关的场景
    List<Scene>  findSceneForQuestionnaire(String questionnaireId);

    Scene findSameSceneName(String sceneName);
}