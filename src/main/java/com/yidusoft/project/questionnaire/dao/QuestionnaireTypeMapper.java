package com.yidusoft.project.questionnaire.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireType;
import com.yidusoft.utils.TreeNode;

import java.util.List;

public interface QuestionnaireTypeMapper extends Mapper<QuestionnaireType> {
    List<TreeNode> getTree();

    List<QuestionnaireType> getQueryAll(QuestionnaireType questionnaireType);

    List<QuestionnaireType> findByIdOrPid(String str);

    QuestionnaireType findParentType(String pid);

    QuestionnaireType findSameType(String questionnaireTypeName);
}