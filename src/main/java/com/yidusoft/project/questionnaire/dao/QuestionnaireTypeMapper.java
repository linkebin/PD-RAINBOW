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

    //删除时检查所要删除的是否包含子类型
    QuestionnaireType findDeleteIsContainChild(String id);
    //类型相关联的数量
    List<QuestionnaireType> findQuestionnaireForTypeNum(String userId);
}