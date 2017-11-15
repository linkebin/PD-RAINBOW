package com.yidusoft.project.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireType;
import com.yidusoft.project.questionnaire.service.QuestionnaireTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuForParameter;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.TreeBuilder;
import com.yidusoft.utils.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
@RestController
@RequestMapping("/questionnaire/type")
public class QuestionnaireTypeController {
    @Resource
    private QuestionnaireTypeService questionnaireTypeService;
    private Logger logger = LoggerFactory.getLogger(QuestionnaireTypeController.class);

    @PostMapping
    public Result add(QuestionnaireType questionnaireType) {
        questionnaireTypeService.save(questionnaireType);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        questionnaireTypeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(QuestionnaireType questionnaireType) {
        questionnaireTypeService.update(questionnaireType);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        QuestionnaireType questionnaireType = questionnaireTypeService.findById(id);
        return ResultGenerator.genSuccessResult(questionnaireType);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionnaireType> list = questionnaireTypeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    //问卷类型树
    @PostMapping("/typeTree")
    @ResponseBody
    public Result tree() {
        List<TreeNode> nodes = questionnaireTypeService.getTree();
        List<TreeNode> treeNodes = TreeBuilder.buildByRecursive(nodes);
        if (treeNodes.size() == 0) {
            treeNodes = nodes;
        }
        List<TreeNode> nodeList = questionnaireTypeService.buileTree(nodes);

        for (TreeNode node : nodeList) {
            logger.info(node.toString());
        }


        Result result = ResultGenerator.genSuccessResult(treeNodes);
        logger.info(result.toString() + nodeList.size() + "===" + treeNodes.size() + "==" + nodes.size());
        return result;
    }


    //添加问卷类型
    @PostMapping("/addQuestionnaireType")
    @ResponseBody
    public Result addMenu(String typeJson) {
        QuestionnaireType questionnaireType = JSON.parseObject(typeJson, QuestionnaireType.class);
        synchronized (this) {
            String parentId = questionnaireType.getPid();
            logger.info("pid = " + parentId + "==============");
            String typeId = UUID.randomUUID().toString();
            questionnaireType.setId(typeId);
            questionnaireType.setQuestionnaireTypeCode(CodeHelper.getCode("CD"));
            questionnaireType.setCreateTime(new Date());
            questionnaireType.setDeleted(0);//0为未删除
//            if (questionnaireType.getPid() == null || questionnaireType.getPid().equals("")){
//                questionnaireType.setPid("0");
//            }else{
//                questionnaireType.setPid(parentId);
//            }
            logger.info(questionnaireType.getCreator() + questionnaireType.getQuestionnaireTypeName() + "==========================");
            questionnaireTypeService.save(questionnaireType);

        }

        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/listByPages")
    @ResponseBody
    public Result listByPages(int page,
                              int size, String params) {
        QuestionnaireType questionnaireType = JSON.parseObject(params, QuestionnaireType.class);
        logger.info(questionnaireType.getQuestionnaireTypeCode() + "= ====" + questionnaireType.getQuestionnaireTypeName() + "-----------====================----------");
        PageHelper.startPage(page, size);
        List<QuestionnaireType> typeList = questionnaireTypeService.getQueryAll(questionnaireType);
//       logger.info(typeList.size() + "------------------------------------");
        PageInfo pageInfo = new PageInfo(typeList);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    //逻辑删除，数据还在 批量删除
    @PostMapping("/deleteBacth")
    @ResponseBody
    public Result deleteBacth(String ids) {
        String arr[] = ids.split(",");
        for (String str : arr) {
            // QuestionnaireType questionnaireType = questionnaireTypeService.findById(str);
            List<QuestionnaireType> questionnaireTypes = questionnaireTypeService.findByIdOrPid(str);
            // logger.info(questionnaireType.getQuestionnaireTypeName() + "===========-----====++++");
            for (QuestionnaireType questionnaireType : questionnaireTypes) {
                questionnaireType.setDeleted(1);
                questionnaireTypeService.update(questionnaireType);
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    //查询类型信息
    @PostMapping("/findTypeInfo")
    @ResponseBody
    public Result findMenuInfo(String id) {
        QuestionnaireType questionnaireType = questionnaireTypeService.findById(id);
        return ResultGenerator.genSuccessResult(questionnaireType);
    }

    //修改问卷类型

    @PostMapping("/updateType")
    @ResponseBody
    public Result updateMenu(String typeJson) {
        QuestionnaireType questionnaireType = JSON.parseObject(typeJson, QuestionnaireType.class);
        questionnaireType.setCreateTime(new Date());
        questionnaireTypeService.update(questionnaireType);
        return ResultGenerator.genSuccessResult();
    }


    //查询父类型信息
    @PostMapping("/findParentType")
    @ResponseBody
    public Result findParentType(String pid){
        QuestionnaireType questionnaireType = questionnaireTypeService.findParentType(pid);
        return ResultGenerator.genSuccessResult(questionnaireType);
    }

    /**
     * 判断类型是否存在
     * @param questionnaireTypeName
     * @return
     */
    @PostMapping("/findSameType")
    @ResponseBody
    public Result findSameType(String questionnaireTypeName){
        QuestionnaireType questionnaireType = questionnaireTypeService.findSameType(questionnaireTypeName);
        return ResultGenerator.genSuccessResult(questionnaireType);
    }

    /**
     * 判断类型是否含有子类型
     */
    @PostMapping("/findDeleteIsContainChild")
    @ResponseBody
    public Result findDeleteIsContainChild(String ids){
        String arr[] = ids.split(",");
        QuestionnaireType questionnaireType = null;
        for(String id : arr){
            QuestionnaireType type = questionnaireTypeService.findDeleteIsContainChild(id);
            if (type != null){
                return ResultGenerator.genSuccessResult(type);
            }
        }
        return ResultGenerator.genSuccessResult(questionnaireType);
    }

    /**
     * 查询类型里面问卷的数量
     */
    @PostMapping("/findQuestionnaireForTypeNum")
    public Result findQuestionnaireForTypeNum(){
        List<QuestionnaireType> questionnaireTypes = questionnaireTypeService.findQuestionnaireForTypeNum();
        return ResultGenerator.genSuccessResult(questionnaireTypes);
    }

}
