package com.yidusoft.project.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.Questionnaire;
import com.yidusoft.project.questionnaire.service.QuestionnaireService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Resource
    private QuestionnaireService questionnaireService;


    /***
     * 分页条件查询问卷
     * @param params
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/questionnaireListByPage")
    @ResponseBody
    public Result questionnaireListByPage(String params, int page, int size) {
        Questionnaire questionnaire= JSON.parseObject(params,Questionnaire.class);
        PageHelper.startPage(page, size);
        List<Questionnaire> list =questionnaireService.questionnaireListByPage(questionnaire);
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    /**
     * 添加问卷
     * @param questionnaire
     * @param questionStr
     * @return
     */
    @PostMapping("/addQuestionnaire")
    @ResponseBody
    public Result addQuestionnaire(Questionnaire questionnaire, String questionStr,String tagId,String sceneId) {

        return  questionnaireService.addQuestionnaire( questionnaire,questionStr, tagId, sceneId);
    }


    /**
     * 查询问卷 相关的 标签   场景
     * @param questionnaireId
     * @return
     */
    @PostMapping("/getQuestionnaireInfo")
    @ResponseBody
    public Result getQuestionnaireInfo(String questionnaireId) {
        return    questionnaireService.getQuestionnaireInfo(questionnaireId);
    }


    /**
     * 修改问卷
     * @param questionnaire
     * @param questionStr
     * @return
     */
    @PostMapping("/updateQuestionnaire")
    @ResponseBody
    public Result updateQuestionnaire(Questionnaire questionnaire, String questionStr) {
        try {
            questionnaireService.updateQuestionnaire(questionnaire,questionStr);
        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }
        return ResultGenerator.genSuccessResult();
    }

    /***
     * 问卷失效
     * @param id
     * @return
     */
    @PostMapping("/invalidQuestionnaire")
    @ResponseBody
    public Result invalidQuestionnaire(String id,String state) {
        try {
                Questionnaire questionnaire = questionnaireService.findById(id);
                questionnaire.setQuestionnaireState(Integer.valueOf(state));
                questionnaireService.update(questionnaire);

        } catch (Exception e) {
            return ResultGenerator.genFailResult("操作失败");
        }

        return ResultGenerator.genSuccessResult();
    }


    /***
     * 逻辑删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteQuestionnaire")
    @ResponseBody
    public Result deleteQuestionnaire(String ids) {
        try {
            String arr[] = ids.split(",");
            for (String str : arr) {
                Questionnaire questionnaire = questionnaireService.findById(str);
                questionnaire.setDeleted(1);
                questionnaireService.update(questionnaire);
            }

        } catch (Exception e) {
            return ResultGenerator.genFailResult("删除失败！");
        }

        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取正在使用中的问卷
     * @return
     */
    @GetMapping("/getQuestionnaireByState")
    public Result getQuestionnaireByState(){
        List<Questionnaire> list=questionnaireService.getQuestionnaireByState();
        return ResultGenerator.genSuccessResult(list);
    }
}
