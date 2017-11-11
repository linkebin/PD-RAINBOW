package com.yidusoft.project.questionnaire.service;
import com.yidusoft.project.questionnaire.domain.Scene;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface SceneService extends Service<Scene> {

    List<Scene> questionnaireSceneListByPage(Scene scene);
    //查询量表相关的场景
    List<Scene> findSceneForGauge(String gaugeId);
    //查询问卷相关的场景
    List<Scene>  findSceneForQuestionnaire(String questionnaireId);

    Scene findSameSceneName(String sceneName);
    //查询场景里面问卷的数量
    List<Scene> findQuestionnaireForSceneNum();
}
