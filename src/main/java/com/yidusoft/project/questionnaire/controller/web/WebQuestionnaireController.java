package com.yidusoft.project.questionnaire.controller.web;


import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionFactorMapper;
import com.yidusoft.project.questionnaire.domain.*;
import com.yidusoft.project.questionnaire.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@Controller
@RequestMapping("/web/questionnaire")
public class WebQuestionnaireController {
    @Resource
    private QuestionnaireService questionnaireService ;

    @Resource
    private QuestionnaireQuestionFactorMapper questionnaireQuestionFactorMapper;
    @Resource
    private QuestionnaireTagService questionnaireTagService;
    @Resource
    private SceneService sceneService;
    @Resource
    private GaugeQuestionFactorService gaugeQuestionFactorService;

    //跳转问卷查询页面
    @RequestMapping("/getQuestionnaire")
    public String getQuestionnaire() {
        return "project/questionnaire/questionnaire/questionnaire-list";
    }
    //跳转问卷添加页面
    @RequestMapping("/addQuestionnaire")
    public String addQuestionnaire() {
        return "project/questionnaire/questionnaire/add-questionnaire";
    }
    //跳转到修改页面
    //跳转量表修改页面
    @RequestMapping("/getUpdateQuestionnaire")
    public  String  getUpdateQuestionnaire(String id, Model model){
        Questionnaire questionnaire=questionnaireService.findById(id);
        //查询量表相关的标签
        List<QuestionnaireTag> questionnaireTagList= questionnaireTagService.findTagForQuestionnaire( id);
        //相关联的场景
        List<Scene> sceneList= sceneService.findSceneForQuestionnaire(id);
        //问题因子
        List<QuestionnaireQuestionFactor>  questionnaireQuestionFactorList= questionnaireQuestionFactorMapper.findQuestionnaireQuestionFactor(id);
        //转载问卷因子里面所有的问题id
        List<String> questionnaireQuestionIds=new ArrayList<>();
        for(QuestionnaireQuestionFactor questionFactor:questionnaireQuestionFactorList){
            questionnaireQuestionIds.add(questionFactor.getQuestionId());
        }
        //判断有没有选择量表list3.addAll(list1);  list3.removeAll(list2);
        if(!"".equals(questionnaire.getGaugeId())){
           List<GaugeQuestionFactor> gaugeQuestionFactors= gaugeQuestionFactorService.findGaugeQuestionFactor(questionnaire.getGaugeId());
            List<String> gaugeQuestionIds=new ArrayList<>();
           for(GaugeQuestionFactor gaugeQuestionFactor:  gaugeQuestionFactors){
               gaugeQuestionIds.add(gaugeQuestionFactor.getQuestionId());
           }

           //在量表的基础上新增加的问题
            List<String> newQuestion=new ArrayList<>();
            newQuestion.addAll(questionnaireQuestionIds);
            newQuestion.removeAll(gaugeQuestionIds);
            //新加问题的id
            model.addAttribute("newQuestion",newQuestion);
            //量表的问题id
            model.addAttribute("gaugeQuestionIds",gaugeQuestionIds);

        }else {
            //不存在量表，只有新加问题的id
            model.addAttribute("newQuestion",questionnaireQuestionIds);
            model.addAttribute("gaugeQuestionIds","");
        }
        model.addAttribute("questionnaire",questionnaire);
        model.addAttribute("questionnaireTagList",questionnaireTagList);
        model.addAttribute("questionnaireQuestionFactorList",questionnaireQuestionFactorList);
        model.addAttribute("sceneList",sceneList);
        return "project/questionnaire/questionnaire/update-questionnaire";
    }


}
