package com.yidusoft.project.questionnaire.service;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.core.Service;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnaireAnswerService extends Service<QuestionnaireAnswer> {
    List<QuestionnaireQuestion> questionList(String questionnaireId);

    List<String> getOptionAnswer(List<QuestionnaireQuestion> questionnaireQuestions);

    List<List<QuestionnaireQuestion>> getQuestionnaireByPage(List<QuestionnaireQuestion> questionnaireQuestions);

    List<String> getAnswers(List<QuestionnaireQuestion> questionnaireQuestions);
}
