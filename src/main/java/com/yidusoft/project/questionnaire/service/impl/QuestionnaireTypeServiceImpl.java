package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.project.questionnaire.dao.QuestionnaireTypeMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireType;
import com.yidusoft.project.questionnaire.service.QuestionnaireTypeService;
import com.yidusoft.core.AbstractService;

import com.yidusoft.utils.Security;
import com.yidusoft.utils.TreeNode;
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
public class QuestionnaireTypeServiceImpl extends AbstractService<QuestionnaireType> implements QuestionnaireTypeService {
    @Resource
    private QuestionnaireTypeMapper questionnaireTypeMapper;

    @Override
    public List<TreeNode> getTree() {
        return questionnaireTypeMapper.getTree();
    }

    @Override
    public List<QuestionnaireType> getQueryAll(QuestionnaireType questionnaireType) {
        return questionnaireTypeMapper.getQueryAll(questionnaireType);
    }

    @Override
    public List<TreeNode> buileTree(List<TreeNode> nodes) {
        List<TreeNode> treeNodes = nodes;
        for (int i = 0; i < treeNodes.size(); i++) {
            for (int y = i + 1; y < treeNodes.size(); y++) {
                if (treeNodes.get(i).getName().equals(treeNodes.get(y).getParentId())) {
                    List<TreeNode> nodeList = new ArrayList<>();
                    nodeList.add(treeNodes.get(y));
                    treeNodes.get(i).setChildren(nodeList);
                }
            }
        }


        return treeNodes;
    }

    @Override
    public List<QuestionnaireType> findByIdOrPid(String str) {
        return questionnaireTypeMapper.findByIdOrPid(str);
    }

    @Override
    public QuestionnaireType findParentType(String pid) {
        return questionnaireTypeMapper.findParentType(pid);
    }

    @Override
    public QuestionnaireType findSameType(String questionnaireTypeName) {
        return questionnaireTypeMapper.findSameType(questionnaireTypeName);
    }

    //删除时检查所要删除的是否包含子类型
    @Override
    public QuestionnaireType findDeleteIsContainChild(String id) {
        return questionnaireTypeMapper.findDeleteIsContainChild(id);
    }

    //查询类型相关联问卷的数量
    @Override
    public  List<QuestionnaireType> findQuestionnaireForTypeNum() {
        return questionnaireTypeMapper.findQuestionnaireForTypeNum(Security.getUserId());
    }
}
