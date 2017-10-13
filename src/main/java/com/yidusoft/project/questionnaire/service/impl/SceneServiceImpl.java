package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.SceneMapper;
import com.yidusoft.project.questionnaire.domain.Scene;
import com.yidusoft.project.questionnaire.service.SceneService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class SceneServiceImpl extends AbstractService<Scene> implements SceneService {
    @Resource
    private SceneMapper sceneMapper;

}