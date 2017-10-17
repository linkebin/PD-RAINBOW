package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.Scene;

import java.util.List;

public interface SceneMapper extends Mapper<Scene> {
    List<Scene> questionnaireSceneListByPage(Scene scene);
}