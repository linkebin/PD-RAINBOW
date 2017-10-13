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
}