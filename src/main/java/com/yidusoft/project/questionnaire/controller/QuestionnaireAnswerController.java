package com.yidusoft.project.questionnaire.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireAnswer;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.QuestionnaireAnswerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Controller
@RequestMapping("/questionnaire/answer")
public class QuestionnaireAnswerController {
    @Resource
    private QuestionnaireAnswerService questionnaireAnswerService;

    @PostMapping
    public Result add(QuestionnaireAnswer questionnaireAnswer) {
        questionnaireAnswerService.save(questionnaireAnswer);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        questionnaireAnswerService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(QuestionnaireAnswer questionnaireAnswer) {
        questionnaireAnswerService.update(questionnaireAnswer);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        QuestionnaireAnswer questionnaireAnswer = questionnaireAnswerService.findById(id);
        return ResultGenerator.genSuccessResult(questionnaireAnswer);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionnaireAnswer> list = questionnaireAnswerService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }



    /*======================================分割线==================================*/

    /**
     * 根据问卷id查找问题
     *
     * @param questionnaireId
     * @return
     */

    @RequestMapping(value = "/questionList/{questionnaireId}", method = RequestMethod.GET)
    public String questionList(@PathVariable(value = "questionnaireId") String questionnaireId, Model model) {
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
        List<String> answers = questionnaireAnswerService.getAnswers(questionnaireQuestions);

        model.addAttribute("optionAnswers", optionAnswers);
        model.addAttribute("questionlist", questionlist);
        model.addAttribute("questionnaireQuestionSize", questionnaireQuestionSize);
        model.addAttribute("answers", answers);
        return "project/cube/questionnaire/questionnaire";
    }
}
