package com.yidusoft.project.questionnaire.controller.web;


import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionFactorMapper;
import com.yidusoft.project.questionnaire.domain.Questionnaire;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestionFactor;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTag;
import com.yidusoft.project.questionnaire.domain.Scene;
import com.yidusoft.project.questionnaire.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
        model.addAttribute("questionnaire",questionnaire);
        model.addAttribute("questionnaireQuestionFactorList",questionnaireQuestionFactorList);
        model.addAttribute("questionnaireTagList",questionnaireTagList);
        model.addAttribute("sceneList",sceneList);
        return "project/questionnaire/questionnaire/update-questionnaire";
    }


}
