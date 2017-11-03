package com.yidusoft.project.cube.questionnaire;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.domain.VisitorRegister;
import com.yidusoft.project.business.service.VisitorRegisterService;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.domain.Questionnaire;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.yidusoft.project.questionnaire.service.QuestionnaireAnswerService;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionService;
import com.yidusoft.project.questionnaire.service.QuestionnaireService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/cube/questionnaire/question")
public class CubeQuestionnaireQuestionController {
        @Resource
        private QuestionnaireQuestionService questionnaireQuestionService;
        @Resource
        private QuestionnaireAnswerService questionnaireAnswerService;
        @Resource
        private DataAcquisitionService dataAcquisitionService;
        @Resource
        private QuestionnaireService questionnaireService;
        @Resource
        private VisitorRegisterService visitorRegisterService;


    /**
     * 查询问卷相关的问题
     * @param id
     * @return
     */
    @PostMapping("/findQuestionForQuestionnaire")
    @ResponseBody
    public  Result findQuestionForQuestionnaire(String id){
        List<QuestionnaireQuestion> questionnaireQuestionList= questionnaireQuestionService.findQuestionForQuestionnaire(id);
        return ResultGenerator.genSuccessResult(questionnaireQuestionList);
    }

    /**
     * 查询来访者使用的问卷问题
     * @param questionnaireId
     * @param userId
     * @param acquisitionId
     * @return
     */
    @PostMapping("/findQuestionForVisitor")
    @ResponseBody
    public  Result findQuestionForVisitor(String questionnaireId,String userId,String acquisitionId){
        List<QuestionnaireQuestion> questionnaireQuestionList= questionnaireQuestionService.findQuestionForQuestionnaire(questionnaireId);
        QuestionnaireAnswer questionnaireAnswer=new QuestionnaireAnswer();
        questionnaireAnswer.setUserId(userId);
        questionnaireAnswer.setAcquisitionId(acquisitionId);
        List<QuestionnaireAnswer>questionnaireAnswerList=questionnaireAnswerService.findAnswerForAcquisition(questionnaireAnswer);
        DataAcquisition  dataAcquisition= dataAcquisitionService.findById(acquisitionId);
        Questionnaire  questionnaire=questionnaireService.findById(questionnaireId)   ;
        VisitorRegister visitorRegister=visitorRegisterService.findById(userId);
        Map<String,Object> map=new HashMap<>();
        map.put("questionList",questionnaireQuestionList);
        map.put("answerList",questionnaireAnswerList);
        map.put("dataAcquisition",dataAcquisition);
        map.put("questionnaire",questionnaire);
        map.put("visitorRegister",visitorRegister);
        return ResultGenerator.genSuccessResult(map);
    }


}
