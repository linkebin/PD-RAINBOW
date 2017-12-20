package com.yidusoft.project.cube.questionnaire.web;

import com.yidusoft.project.questionnaire.domain.Questionnaire;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.QuestionnaireAnswerService;
import com.yidusoft.project.questionnaire.service.QuestionnaireService;
import com.yidusoft.utils.Security;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.yidusoft.configurer.ResourcesStatic.GAUGE;

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





    @RequestMapping(value ={"/getQuestionnaireGuide"})
    public String getQuestionnaireGuide(String questionnaireId,String userId,String visitorTimes,String activityId,String userName,Model model){
        model.addAttribute("questionnaireId",questionnaireId);
        model.addAttribute("activityId",activityId);
        model.addAttribute("userId",userId);
        model.addAttribute("visitorTimes",visitorTimes);
        model.addAttribute("userName",userName);
        Questionnaire questionnaire= questionnaireService.findById(questionnaireId);
        model.addAttribute("guide",questionnaire.getGuide());
        return "project/cube/questionnaire/questionnaire-guide";
    }

    @RequestMapping(value ={"/getQuestionnairePreviewGuide"})
    public String getQuestionnairePreviewGuide(String questionnaireId,String userId,String visitorTimes,String activityId,String userName,Model model){
        model.addAttribute("questionnaireId",questionnaireId);
        model.addAttribute("activityId",activityId);
        model.addAttribute("userId",userId);
        model.addAttribute("visitorTimes",visitorTimes);
        model.addAttribute("userName",userName);
        Questionnaire questionnaire= questionnaireService.findById(questionnaireId);
        model.addAttribute("guide",questionnaire.getGuide());
        return "project/cube/questionnaire/questionnaire-preview-guide";
    }


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
        Questionnaire questionnaire=questionnaireService.findQuestionnaireType(questionnaireId);
        model.addAttribute("questionnaire",questionnaire);
        if(!StringUtils.isEmpty(activityId)) {
            return "project/cube/questionnaire/other-questionnaire";
        }
        //判断问卷的类型 1 左右滑动 2  平铺
        if(questionnaire.getAnswerModelType()==1
                && !("生活事件量表(LES)").equals(questionnaire.getGaugeName())
                && !("长处和困难问卷(SDQ)").equals(questionnaire.getGaugeName())
                && !("匹兹堡睡眠质量指数(PSQI)").equals(questionnaire.getGaugeName())
                ){
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
        }else if(("生活事件量表(LES)").equals(questionnaire.getGaugeName())){
            return "project/cube/questionnaireFilling/fillIn_gauge_12";
        }else if(("长处和困难问卷(SDQ)").equals(questionnaire.getGaugeName())){
            return "project/cube/questionnaireFilling/fillIn_gauge_7";
        }else  if(("匹兹堡睡眠质量指数(PSQI)").equals(questionnaire.getGaugeName())){
            return "project/cube/questionnaireFilling/fillIn_gauge_10";
        }else {
            return "project/cube/questionnaire/vertical-questionnaire";
        }


    }

    /**
     * 查看来访者的填写之后的问卷
     * @param questionnaireId
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value ={"/getQuestionnaireForVisitorAll"})
    public String getQuestionnaireForVisitor(String acquisitionId,String questionnaireId,String userId, Model model){
        model.addAttribute("questionnaireId",questionnaireId);
        model.addAttribute("userId",userId);
        model.addAttribute("acquisitionId",acquisitionId);
        Questionnaire questionnaire = questionnaireService.findQuestionnaireType(questionnaireId);
        return "project/cube/customer/vertical-questionnaire-customer";
    }

    /**
     * 查看来访者的填写之后的问卷(所有问卷填写详情的跳转)
     * @param questionnaireId
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value ={"/getQuestionnaireForVisitor"})
    public String getQuestionnaireForVisitorAll(String acquisitionId,String questionnaireId,String userId, Model model){
        model.addAttribute("questionnaireId",questionnaireId);
        model.addAttribute("userId",userId);
        model.addAttribute("acquisitionId",acquisitionId);
        Questionnaire questionnaires=questionnaireService.findById(questionnaireId);
        if("长处和困难问卷(SDQ)家长版".equals(questionnaires.getQuestionnaireName())){
            return "project/cube/questionnaireDetails/gauge_7_b";
        }else if("长处和困难问卷(SDQ)老师版".equals(questionnaires.getQuestionnaireName())) {
            return "project/cube/questionnaireDetails/gauge_7_c";
        }else if("长处和困难问卷(SDQ)学生版".equals(questionnaires.getQuestionnaireName())){
            return "project/cube/questionnaireDetails/gauge_7_a";
        }
        Questionnaire questionnaire = questionnaireService.findQuestionnaireType(questionnaireId);
        String  htmlName= GAUGE.get(questionnaire.getGaugeName()).toString();

        return "project/cube/questionnaireDetails/"+htmlName;
    }

    @RequestMapping(value ={"/getSuccess"})
    public String getSuccess(){
        return "project/cube/questionnaire/questionnaire-fillIn-success";
    }

   /* @RequestMapping(value ={"/getQuestionnaireFill"})
    public String getQuestionnaireFill(String questionnaireId,String userId,String visitorTimes,String activityId,String userName,Model model) {
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("activityId", activityId);
        model.addAttribute("userId", userId);
        model.addAttribute("visitorTimes", visitorTimes);
        model.addAttribute("userName", userName);
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        model.addAttribute("questionnaire", questionnaire);
        if (("生活事件量表(LES)").equals(questionnaire.getQuestionnaireName())) {
            return "project/cube/questionnaireFilling/fillIn_gauge_15";
        } else if (("长处和困难问卷(SDQ)").equals(questionnaire.getQuestionnaireName())) {
            return "project/cube/questionnaireFilling/fillIn_gauge_13";
        } else if (("匹兹堡睡眠质量指数(PSQI)").equals(questionnaire.getQuestionnaireName())) {
            return "project/cube/questionnaireFilling/fillIn_gauge_14";
        }
        return "project/cube/questionnaire/other-questionnaire";
    }*/
}
