package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.QuestionnaireAnswerMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.QuestionnaireAnswerService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireAnswerServiceImpl extends AbstractService<QuestionnaireAnswer> implements QuestionnaireAnswerService {
    @Resource
    private QuestionnaireAnswerMapper questionnaireAnswerMapper;

    @Override
    public List<QuestionnaireQuestion> questionList(String questionnaireId) {
        return questionnaireAnswerMapper.questionList(questionnaireId);
    }

    @Override
    public List<String> getOptionAnswer(List<QuestionnaireQuestion> questionnaireQuestions) {
        QuestionnaireQuestion questionnaireQuestion = questionnaireQuestions.get(0);
        String[] optionAnswers = questionnaireQuestion.getOptionAnswer().split("\\|\\|");

        List<String> optionAnswerList = new ArrayList<>();
        for (int i = 0; i < optionAnswers.length; i++) {
            optionAnswerList.add(optionAnswers[i]);
        }
        return optionAnswerList;
    }

    @Override
    public List<List<QuestionnaireQuestion>> getQuestionnaireByPage(List<QuestionnaireQuestion> questionnaireQuestions) {
        List<List<QuestionnaireQuestion>> questionListList = new ArrayList<>();

        //满十道题的页数
        int pageSize = questionnaireQuestions.size() / 10;

        //不满十道题的题目数量
        int questionSize = questionnaireQuestions.size() % 10;

        //组装题目的index
        int index = 0;

        //组装满十道题的list
        for (int i = 0; i < pageSize; i++) {
            List<QuestionnaireQuestion> questions = new ArrayList<>();
            for (int y = 0; y < 10; y++) {
                questions.add(questionnaireQuestions.get(index));
                index++;
            }
            questionListList.add(questions);
        }

        //组装满十道题的list
        if (questionSize > 0) {
            List<QuestionnaireQuestion> questions = new ArrayList<>();
            for (int i = 0; i < questionSize; i++) {
                questions.add(questionnaireQuestions.get(index));
            }
            questionListList.add(questions);
        }
        return questionListList;
    }

    @Override
    public List<String> getAnswers(List<QuestionnaireQuestion> questionnaireQuestions) {
        QuestionnaireQuestion questionnaireQuestion = questionnaireQuestions.get(0);
        String[] answers = questionnaireQuestion.getOptionScore().split("\\|\\|");

        List<String> answerList = new ArrayList<>();
        for (int i = 0; i < answers.length; i++) {
            answerList.add(answers[i]);
        }
        return answerList;
    }

}
