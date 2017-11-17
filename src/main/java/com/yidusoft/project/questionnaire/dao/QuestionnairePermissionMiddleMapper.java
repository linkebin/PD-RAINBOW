package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.QuestionnairePermissionMiddle;
import com.yidusoft.project.system.domain.SecUser;

import java.util.List;

/**
 * Created by yhdj on 2017/11/14.
 */
public interface QuestionnairePermissionMiddleMapper extends Mapper<QuestionnairePermissionMiddle> {
    List<SecUser> findQuestionnaireSecUser(SecUser secUser);

    List<SecUser> findSecUserByQuestionnaireId(String questionnaireId);

    List<QuestionnairePermissionMiddle> findPermission(String questionnaireId);
}
