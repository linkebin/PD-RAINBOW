package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireQuestionServiceImpl extends AbstractService<QuestionnaireQuestion> implements QuestionnaireQuestionService {
    @Resource
    private QuestionnaireQuestionMapper questionnaireQuestionMapper;

    //分页条件查询问题
    @Override
    public List<QuestionnaireQuestion> questionListByPage(QuestionnaireQuestion questionnaireQuestion) {
        return questionnaireQuestionMapper.questionListByPage(questionnaireQuestion);
    }
}
