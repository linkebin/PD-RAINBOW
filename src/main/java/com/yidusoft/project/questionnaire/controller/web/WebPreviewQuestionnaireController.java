package com.yidusoft.project.questionnaire.controller.web;

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
 * Created by yhdj on 2017/10/31.
 * 跳转到问卷预览
 */

@Controller
@RequestMapping("/web/questionnaire")
public class WebPreviewQuestionnaireController {

    @Resource
    private QuestionnaireService questionnaireService;
    @Resource
    private QuestionnaireAnswerService questionnaireAnswerService;

    @RequestMapping("/questionnairePreview")
    public String questionnairePreview(){
        return "project/questionnaire/questionnairePreview/questionnairePreview";
    }

    //跳转到横版或竖版问卷预览
    @RequestMapping("/question_horizontal_or_vertital_preview")
    public String questionnaireHorizontalPreview(String questionnaireId, Model model){
        model.addAttribute("questionnaireId",questionnaireId);

        Questionnaire questionnaire= questionnaireService.findById(questionnaireId);
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

            model.addAttribute("optionAnswers", optionAnswers);
            model.addAttribute("questionlist", questionlist);
            model.addAttribute("questionnaireQuestionSize", questionnaireQuestionSize);
            model.addAttribute("scoreList", answers);
           return "project/questionnaire/questionnairePreview/questionnaire_horizontal_preview";

        }else {
            return "project/questionnaire/questionnairePreview/questionnaire_vertical_preview";
        }

    }
}
