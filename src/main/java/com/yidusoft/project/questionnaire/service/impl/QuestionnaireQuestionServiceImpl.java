package com.yidusoft.project.questionnaire.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.dao.DataAcquisitionMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireAnswerMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionMapper;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionService;
import com.yidusoft.core.AbstractService;

import com.yidusoft.project.transaction.service.UserQuestionnairesService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.MyException;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireQuestionServiceImpl extends AbstractService<QuestionnaireQuestion> implements QuestionnaireQuestionService {
    @Resource
    private QuestionnaireQuestionMapper questionnaireQuestionMapper;
    @Resource
    private DataAcquisitionMapper dataAcquisitionMapper;
    @Resource
    private QuestionnaireAnswerMapper questionnaireAnswerMapper;
    @Resource
    private UserQuestionnairesService userQuestionnairesService;
    //分页条件查询问题
    @Override
    public List<QuestionnaireQuestion> questionListByPage(QuestionnaireQuestion questionnaireQuestion) {
        return questionnaireQuestionMapper.questionListByPage(questionnaireQuestion);
    }


    //问卷或量表  添加问题 查询没有添加的问题
    @Override
    public List<QuestionnaireQuestion> findQuestionBYid(String ids) {
        Map<String,Object> map=new HashMap<>();
        String [] idss=ids.split(",");
        map.put("ids",ids.split(","));
        return questionnaireQuestionMapper.findQuestionBYid(map);
    }
    // 查询问卷相关的问题
    @Override
    public List<QuestionnaireQuestion> findQuestionForQuestionnaire(String id) {
        return questionnaireQuestionMapper.findQuestionForQuestionnaire(id);
    }

    //提交问卷
    @Override
    public Result submitQuestionnaire(String param, String questionnaireId,String userId) {
        try {
           //判断账号余额是否够
           if(!userQuestionnairesService.flgBalance()){
                throw  new MyException("账号余额不足！");
           }

            List<Map<String,Object>> mapList= (List<Map<String,Object>>)JSON.parse(param);
           //问卷使用的id
           String dataAcquisitionId=UUID.randomUUID().toString();
           //总分
           int totalScore=0;
           for(Map map:mapList){
             //分数
            int score=Integer.valueOf(map.get("score").toString());
             totalScore+= score;
             //问题id
             String questionId=map.get("questionId").toString();
             //答案
             String answer=map.get("answer").toString();
             QuestionnaireAnswer questionnaireAnswer=new QuestionnaireAnswer();
             questionnaireAnswer.setId(UUID.randomUUID().toString());
             questionnaireAnswer.setAcquisitionId(dataAcquisitionId);
             questionnaireAnswer.setAnswer(answer);
             questionnaireAnswer.setQuestionId(questionId);
             questionnaireAnswer.setCreateTime(new Date());
             questionnaireAnswer.setCreator(Security.getUser().getUserName());
             questionnaireAnswer.setDeleted(0);
             questionnaireAnswer.setUserId(userId);
             questionnaireAnswerMapper.insert(questionnaireAnswer);
         }
        DataAcquisition dataAcquisition=new DataAcquisition();
        dataAcquisition.setId(dataAcquisitionId);
        //dataAcquisition.setActivityId();
        dataAcquisition.setDataQuestion(questionnaireId);
        dataAcquisition.setDataCode(CodeHelper.getCode("SY"));
        dataAcquisition.setTotalScore(totalScore);
        dataAcquisition.setUserId(userId);
        dataAcquisition.setDeleted(0);
        dataAcquisition.setDataResult("结果");
        dataAcquisition.setDataUser(Security.getUser().getUserName());
        dataAcquisition.setCreateTime(new Date());
        dataAcquisitionMapper.insert(dataAcquisition);

        //扣除余额
         userQuestionnairesService.deduction();
        }catch (Exception e){
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }
}
