package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.questionnaire.dao.QuestionnairePermissionMiddleMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.domain.QuestionnairePermissionMiddle;
import com.yidusoft.project.questionnaire.service.QuestionnaireAnswerService;
import com.yidusoft.project.questionnaire.service.QuestionnairePermissionMiddleService;
import com.yidusoft.project.system.domain.SecUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yhdj on 2017/11/14.
 */
@Service
@Transactional
public class QuestionnairePermissionMiddleServiceImpl extends AbstractService<QuestionnairePermissionMiddle> implements QuestionnairePermissionMiddleService {
   @Resource
   private QuestionnairePermissionMiddleMapper questionnairePermissionMiddleMapper;
    @Override
    public List<SecUser> findQuestionnaireSecUser(SecUser secUser) {
        return questionnairePermissionMiddleMapper.findQuestionnaireSecUser(secUser);
    }
}
