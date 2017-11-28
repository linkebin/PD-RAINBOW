package com.yidusoft.project.cube.questionnaire;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.domain.ActiveParticipant;
import com.yidusoft.project.business.domain.VisitorRegister;
import com.yidusoft.project.business.service.ActiveParticipantService;
import com.yidusoft.project.business.service.VisitorRegisterService;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.domain.Questionnaire;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.yidusoft.project.questionnaire.service.QuestionnaireAnswerService;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionService;
import com.yidusoft.project.questionnaire.service.QuestionnaireService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private ActiveParticipantService activeParticipantService;

    /**
     * 查询问卷相关的问题
     *
     * @param id
     * @return
     */
    @PostMapping("/findQuestionForQuestionnaire")
    @ResponseBody
    public Result findQuestionForQuestionnaire(String id) {
        List<QuestionnaireQuestion> questionnaireQuestionList = questionnaireQuestionService.findQuestionForQuestionnaire(id);
        return ResultGenerator.genSuccessResult(questionnaireQuestionList);
    }

    /**
     * 查询来访者使用的问卷问题
     *
     * @param questionnaireId
     * @param userId
     * @param acquisitionId
     * @return
     */
    @PostMapping("/findQuestionForVisitor")
    @ResponseBody
    public Result findQuestionForVisitor(String questionnaireId, String userId, String acquisitionId) {
        List<QuestionnaireQuestion> questionnaireQuestionList = questionnaireQuestionService.findQuestionForQuestionnaire(questionnaireId);
        QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();
        questionnaireAnswer.setUserId(userId);
        questionnaireAnswer.setAcquisitionId(acquisitionId);
        List<QuestionnaireAnswer> questionnaireAnswerList = questionnaireAnswerService.findAnswerForAcquisition(questionnaireAnswer);
        DataAcquisition dataAcquisition = dataAcquisitionService.findById(acquisitionId);
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        VisitorRegister visitorRegister = visitorRegisterService.findById(userId);
        ActiveParticipant activeParticipant = null;
        if (visitorRegister == null) {
            activeParticipant = activeParticipantService.findById(userId);
        }
        Map<String, Object> map = new HashMap<>();
        //对于生活事件量表的特殊数据 将答案封装成map
        Map<String,Object> answerMap=new HashMap<>();
         for(QuestionnaireAnswer qa : questionnaireAnswerList){
             answerMap.put(qa.getQuestionId(),qa.getAnswer());
         }
        map.put("questionList", questionnaireQuestionList);
        map.put("answerList", questionnaireAnswerList);
        //生活事件量表的答案map
        map.put("answerMap", answerMap);
        map.put("dataAcquisition", dataAcquisition);
        map.put("questionnaire", questionnaire);
        map.put("visitorRegister", visitorRegister);
        map.put("activeParticipant", activeParticipant);
        return ResultGenerator.genSuccessResult(map);
    }


}
