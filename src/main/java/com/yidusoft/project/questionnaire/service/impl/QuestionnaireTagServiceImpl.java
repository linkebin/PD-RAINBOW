package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.QuestionnaireTagMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTag;
import com.yidusoft.project.questionnaire.service.QuestionnaireTagService;
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
public class QuestionnaireTagServiceImpl extends AbstractService<QuestionnaireTag> implements QuestionnaireTagService {
    @Resource
    private QuestionnaireTagMapper questionnaireTagMapper;

    @Override
    public List<QuestionnaireTag> questionnaireTagListByPage(QuestionnaireTag questionnaireTag) {
        return questionnaireTagMapper.questionnaireTagListByPage(questionnaireTag);
    }
    //查询量表相关的标签
    @Override
    public List<QuestionnaireTag> findTagForGauge(String gaugeId) {
        return questionnaireTagMapper.findTagForGauge(gaugeId);
    }
    //查询问卷相关的标签
    @Override
    public List<QuestionnaireTag> findTagForQuestionnaire(String questionnaireId) {
        return questionnaireTagMapper.findTagForQuestionnaire(questionnaireId);
    }

    @Override
    public QuestionnaireTag findSameTag(String tagName) {
        return questionnaireTagMapper.findSameTag(tagName);
    }
    //查询这个标签有多少个问卷使用
    @Override
    public List<QuestionnaireTag> findQuestionnaireForTagNum() {
        return questionnaireTagMapper.findQuestionnaireForTagNum(Security.getUserId());
    }
}
