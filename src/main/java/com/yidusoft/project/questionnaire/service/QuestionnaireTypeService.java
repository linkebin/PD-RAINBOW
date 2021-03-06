package com.yidusoft.project.questionnaire.service;
import com.yidusoft.project.questionnaire.domain.QuestionnaireType;
import com.yidusoft.core.Service;
import com.yidusoft.utils.TreeNode;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */


public interface QuestionnaireTypeService extends Service<QuestionnaireType> {

    List<TreeNode> getTree();

    List<QuestionnaireType> getQueryAll(QuestionnaireType questionnaireType);

    List<TreeNode> buileTree(List<TreeNode> nodes);

    List<QuestionnaireType> findByIdOrPid(String str);

    QuestionnaireType findParentType(String pid);

    //增加时检查是否类型已经存在
    QuestionnaireType findSameType(String questionnaireTypeName);

    //删除是检查所要删除的是否包含子类型
    QuestionnaireType findDeleteIsContainChild(String id);
    //类型相关联的数量
    List<QuestionnaireType> findQuestionnaireForTypeNum();
}
