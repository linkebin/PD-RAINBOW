package com.yidusoft.project.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTag;
import com.yidusoft.project.questionnaire.domain.QuestionnaireType;
import com.yidusoft.project.questionnaire.service.QuestionnaireTagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
@RestController
@RequestMapping("/questionnaire/tag")
public class QuestionnaireTagController {
    @Resource
    private QuestionnaireTagService questionnaireTagService;
    private Logger logger = LoggerFactory.getLogger(QuestionnaireTag.class);

    @PostMapping
    public Result add(QuestionnaireTag questionnaireTag) {
        questionnaireTagService.save(questionnaireTag);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        questionnaireTagService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(QuestionnaireTag questionnaireTag) {
        questionnaireTagService.update(questionnaireTag);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        QuestionnaireTag questionnaireTag = questionnaireTagService.findById(id);
        return ResultGenerator.genSuccessResult(questionnaireTag);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionnaireTag> list = questionnaireTagService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    /*======================= 分割线==================================   */

    /***
     * 添加或者修改问卷标签
     * @param typeJson
     * @return
     */
    @PostMapping("/addORupdateQuestionnaireTag")
    @ResponseBody
    public Result addORupdateQuestion(String typeJson) {
        QuestionnaireTag questionnaireTag = JSON.parseObject(typeJson, QuestionnaireTag.class);

        try {
            if ("".equals(questionnaireTag.getId())) {

                questionnaireTag.setId(UUID.randomUUID().toString());
                questionnaireTag.setCreateTime(new Date());
                questionnaireTag.setCreator(Security.getUser().getUserName());
                questionnaireTag.setDeleted(0);
                questionnaireTag.setTagCode(CodeHelper.getCode("WQT"));
                questionnaireTagService.save(questionnaireTag);
            } else {
                questionnaireTagService.update(questionnaireTag);
            }
        } catch (Exception e) {
            return ResultGenerator.genFailResult("操作失败");
        }
        return ResultGenerator.genSuccessResult();

    }


    /**
     * 分页条件查询所有的问卷标签
     *
     * @param params
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/questionnaireTagListByPage")
    @ResponseBody
    public Result questionListByPage(String params, int page, int size) {
        QuestionnaireTag questionnaireTag = JSON.parseObject(params, QuestionnaireTag.class);
        PageHelper.startPage(page, size);
        List<QuestionnaireTag> list = questionnaireTagService.questionnaireTagListByPage(questionnaireTag);
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    /***
     * 逻辑删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteQuestionnaireTag")
    @ResponseBody
    public Result deleteQuestion(String ids) {
        try {
            String arr[] = ids.split(",");
            for (String str : arr) {
                QuestionnaireTag questionnaireTag = questionnaireTagService.findById(str);
                questionnaireTag.setDeleted(1);
                questionnaireTagService.update(questionnaireTag);
            }

        } catch (Exception e) {
            return ResultGenerator.genFailResult("删除失败！");
        }

        return ResultGenerator.genSuccessResult();
    }


    /**
     * 修改之前 查询的问卷标签信息
     * @param id
     * @return
     */
    @PostMapping("/findQuestionnaireTagForUpdate")
    @ResponseBody
    public  Result findQuestionForUpdate(String id){
        QuestionnaireTag questionnaireTag= questionnaireTagService.findById(id);

        return ResultGenerator.genSuccessResult(questionnaireTag);
    }

    /**
     * 增加前查询是否有重复的标签名
     * @param tagName
     * @return
     */
    @PostMapping("/findSameTag")
    @ResponseBody
    public Result findSameTag(String tagName){
        QuestionnaireTag questionnaireTag = questionnaireTagService.findSameTag(tagName);
        return ResultGenerator.genSuccessResult(questionnaireTag);
    }

}
