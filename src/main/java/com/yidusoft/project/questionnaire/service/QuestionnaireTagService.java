package com.yidusoft.project.questionnaire.service;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTag;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnaireTagService extends Service<QuestionnaireTag> {

    List<QuestionnaireTag> questionnaireTagListByPage(QuestionnaireTag questionnaireTag);
}
