package com.yidusoft.project.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

    /**
    * Created by CodeGenerator on 2017/10/11.
    */
    @RestController
    @RequestMapping("/questionnaire/question")
    public class QuestionnaireQuestionController {
            @Resource
            private QuestionnaireQuestionService questionnaireQuestionService;



            /**
             * 分页条件查询所有的问题
             * @param params
             * @param page
             * @param size
             * @return
             */
            @PostMapping("/questionListByPage")
            @ResponseBody
            public Result questionListByPage(String params, int page, int size) {
                QuestionnaireQuestion questionnaireQuestion=JSON.parseObject(params,QuestionnaireQuestion.class);
                PageHelper.startPage(page, size);
                List<QuestionnaireQuestion> list =questionnaireQuestionService.questionListByPage(questionnaireQuestion);
                //查询所有的相关数据
                PageInfo pageInfo = new PageInfo(list);
                return ResultGenerator.genSuccessResult(pageInfo);
            }

            /***
             * 添加或者修改问题
             * @param questionnaireQuestion
             * @return
             */
            @PostMapping("/addORupdateQuestion")
            @ResponseBody
            public Result addORupdateQuestion(QuestionnaireQuestion questionnaireQuestion) {
                  try {
                     if("".equals(questionnaireQuestion.getId())){

                         questionnaireQuestion.setId(UUID.randomUUID().toString());
                         questionnaireQuestion.setCreateTime(new Date());
                         questionnaireQuestion.setCreator(Security.getUser().getUserName());
                         questionnaireQuestion.setDeleted(0);
                         questionnaireQuestion.setQuestionCode(CodeHelper.getCode("WT"));
                         questionnaireQuestionService.save(questionnaireQuestion);
                       }else {
                         questionnaireQuestionService.update(questionnaireQuestion);
                       }
                  }catch (Exception e){
                      return ResultGenerator.genFailResult("操作失败");
                  }
                return ResultGenerator.genSuccessResult();
            }

        /**
         * 修改之前 查询的问题信息
         * @param id
         * @return
         */
        @PostMapping("/findQuestionForUpdate")
        @ResponseBody
        public  Result findQuestionForUpdate(String id){
            QuestionnaireQuestion questionnaireQuestion= questionnaireQuestionService.findById(id);

            return ResultGenerator.genSuccessResult(questionnaireQuestion);
        }

        /***
         * 逻辑删除
         * @param ids
         * @return
         */
        @PostMapping("/deleteQuestion")
        @ResponseBody
        public  Result deleteQuestion(String ids){
            try{
                String arr [] = ids.split(",");
                for(String str : arr){
                    QuestionnaireQuestion questionnaireQuestion = questionnaireQuestionService.findById(str);
                    questionnaireQuestion.setDeleted(1);
                    questionnaireQuestionService.update(questionnaireQuestion);
                }

            }catch (Exception e){
                return  ResultGenerator.genFailResult("删除失败！");
            }

            return ResultGenerator.genSuccessResult();
        }

        /***
         * 问题查询
         * @param ids
         * @return
         */
        @PostMapping("/findQuestion")
        @ResponseBody
        public  Result findQuestion(String ids){
            List<QuestionnaireQuestion> questionnaireQuestions=new ArrayList<>();
                String arr [] = ids.split(",");
                for(String str : arr){
                    QuestionnaireQuestion questionnaireQuestion = questionnaireQuestionService.findById(str);
                    questionnaireQuestions.add(questionnaireQuestion);
                }

            return ResultGenerator.genSuccessResult(questionnaireQuestions);
        }

        /**
         * 问卷或量表  添加问题 查询没有添加的问题
         * @param params
         * @param page
         * @param size
         * @return
         */
        @PostMapping("/findQuestionBYid")
        @ResponseBody
        public Result findQuestionBYid(String params, int page, int size) {
            QuestionnaireQuestion questionnaireQuestion=JSON.parseObject(params,QuestionnaireQuestion.class);
            PageHelper.startPage(page, size);
            List<QuestionnaireQuestion> list =questionnaireQuestionService.findQuestionBYid(questionnaireQuestion.getId());
            //查询所有的相关数据
            PageInfo pageInfo = new PageInfo(list);
            return ResultGenerator.genSuccessResult(pageInfo);
        }



    /*------------------下面系统自动生成            分割--------------------------------*/


    @PostMapping
    public Result add(QuestionnaireQuestion questionnaireQuestion) {
        questionnaireQuestionService.save(questionnaireQuestion);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        questionnaireQuestionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(QuestionnaireQuestion questionnaireQuestion) {
        questionnaireQuestionService.update(questionnaireQuestion);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        QuestionnaireQuestion questionnaireQuestion = questionnaireQuestionService.findById(id);
        return ResultGenerator.genSuccessResult(questionnaireQuestion);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionnaireQuestion> list = questionnaireQuestionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
