package com.yidusoft.project.cube.questionnaire.web;

import com.yidusoft.project.questionnaire.domain.Questionnaire;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.QuestionnaireAnswerService;
import com.yidusoft.project.questionnaire.service.QuestionnaireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zcb on 2017/10/23.
 */
@Controller
@RequestMapping(value ={"/web/cube/"})
public class WebCubeQuestionnaireController {

    @Resource
    private QuestionnaireService questionnaireService;
    @Resource
    private QuestionnaireAnswerService questionnaireAnswerService;

    /**
     * 跳转到问卷填写
     * @return
     */
    @RequestMapping(value ={"/getQuestionnaireFillIn"})
    public String getQuestionnaireFillIn(String questionnaireId,String userId,String visitorTimes,String activityId,String userName,Model model){
        model.addAttribute("questionnaireId",questionnaireId);
        model.addAttribute("activityId",activityId);
        model.addAttribute("userId",userId);
        model.addAttribute("visitorTimes",visitorTimes);
        model.addAttribute("userName",userName);
        Questionnaire questionnaire= questionnaireService.findById(questionnaireId);
        model.addAttribute("questionnaire",questionnaire);
        //判断问卷的类型 1 左右滑动 2  平铺
        if(questionnaire.getAnswerModelType()==1){
            List<QuestionnaireQuestion> questionnaireQuestions = null;
            if (questionnaireId != null && questionnaireId != "") {
                questionnaireQuestions = questionnaireAnswerService.questionList(questionnaireId);
            }

            //获取选项
            List<String> optionAnswers = questionnaireAnswerService.getOptionAnswer(questionnaireQuestions);

            //获取问题，每10道为一页
            List<List<QuestionnaireQuestion>> questionlist = questionnaireAnswerService.getQuestionnaireByPage(questionnaireQuestions);

            //获取题目数量
            int questionnaireQuestionSize = questionnaireQuestions.size();

            //获取问题答案
            List<List<String>> answers = questionnaireAnswerService.getAnswers(questionnaireQuestions);
            //获取问卷注意事项
            String remarks = questionnaire.getRemarks();
            model.addAttribute("remarks",remarks);
            model.addAttribute("optionAnswers", optionAnswers);
            model.addAttribute("questionlist", questionlist);
            model.addAttribute("questionnaireQuestionSize", questionnaireQuestionSize);
            model.addAttribute("scoreList", answers);
            return "project/cube/questionnaire/questionnaire";
        }else {
            return "project/cube/questionnaire/vertical-questionnaire";
        }


    }

    /**
     * 查看来访者的填写问卷
     * @param questionnaireId
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value ={"/getQuestionnaireForVisitor"})
    public String getQuestionnaireForVisitor(String acquisitionId,String questionnaireId,String userId, Model model){
        model.addAttribute("questionnaireId",questionnaireId);
        model.addAttribute("userId",userId);
        model.addAttribute("acquisitionId",acquisitionId);
        return "project/cube/customer/vertical-questionnaire-customer";
    }
    @RequestMapping(value ={"/getSuccess"})
    public String getSuccess(){
        return "project/cube/questionnaire/questionnaire-fillIn-success";
    }

    @RequestMapping(value ={"/getQuestionnaireFill"})
    public String getQuestionnaireFill(String questionnaireId,String userId,String visitorTimes,String activityId,String userName,Model model){
        model.addAttribute("questionnaireId",questionnaireId);
        model.addAttribute("activityId",activityId);
        model.addAttribute("userId",userId);
        model.addAttribute("visitorTimes",visitorTimes);
        model.addAttribute("userName",userName);
        Questionnaire questionnaire= questionnaireService.findById(questionnaireId);
        model.addAttribute("questionnaire",questionnaire);
        return "project/cube/questionnaire/other-questionnaire";
    }
}
