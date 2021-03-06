package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.SceneMapper;
import com.yidusoft.project.questionnaire.domain.Scene;
import com.yidusoft.project.questionnaire.service.SceneService;
import com.yidusoft.core.AbstractService;

import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class SceneServiceImpl extends AbstractService<Scene> implements SceneService {
    @Resource
    private SceneMapper sceneMapper;

    @Override
    public List<Scene> questionnaireSceneListByPage(Scene scene) {
        return sceneMapper.questionnaireSceneListByPage(scene);
    }

    //查询量表相关的场景
    @Override
    public List<Scene> findSceneForGauge(String gaugeId) {
        return sceneMapper.findSceneForGauge(gaugeId);
    }
    //查询量表问卷的场景
    @Override
    public List<Scene> findSceneForQuestionnaire(String questionnaireId) {
        return sceneMapper.findSceneForQuestionnaire(questionnaireId);
    }

    //查询是否有重复的场景
    @Override
    public Scene findSameSceneName(String sceneName) {
        return sceneMapper.findSameSceneName(sceneName);
    }
    //查询场景里面问卷的数量
    @Override
    public List<Scene> findQuestionnaireForSceneNum() {
        return sceneMapper.findQuestionnaireForSceneNum(Security.getUserId());
    }
}
