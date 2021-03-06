package com.yidusoft.project.questionnaire.controller.web;


import com.yidusoft.configurer.ResourcesStatic;
import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionFactorMapper;
import com.yidusoft.project.questionnaire.domain.*;
import com.yidusoft.project.questionnaire.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private QuestionnaireAnswerService questionnaireAnswerService;

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
            if(questionnaire.getSort() != null && questionnaire.getSort() != ""){
                String[] temp =   questionnaire.getSort().split(",");
                questionnaireQuestionIds = new ArrayList<>();
                for (String d : temp){
                    questionnaireQuestionIds.add(d);
                }
                model.addAttribute("ids",questionnaireQuestionIds);
            }else{
                model.addAttribute("ids",questionnaireQuestionIds);
            }

            List<Integer> newIdIndex = new ArrayList<>();
            for(int i = 0; i < questionnaireQuestionIds.size(); i++){
                for(int y = 0; y < newQuestion.size(); y++){
                    if(questionnaireQuestionIds.get(i).equals(newQuestion.get(y))){
                        newIdIndex.add(i);
                    }
                }
            }
            model.addAttribute("newIdIndex",newIdIndex);
            System.out.println(questionnaireQuestionIds);
            System.out.println(newIdIndex);
            model.addAttribute("sortedQuestionIds",questionnaireQuestionIds);
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


    /***
     * 跳转到横版或竖版问卷预览
     *修改人：张成博
     *修改内容：将问卷的预览横板显示改为竖版
     *修改时间：2018-04-08
     * @param questionnaireId
     * @param model
     * @return
     */
    @RequestMapping(value = "/question_horizontal_or_vertital_preview")
    public String questionnaireHorizontalPreview(String questionnaireId, Model model){
        model.addAttribute("questionnaireId",questionnaireId);
        Questionnaire questionnaire= questionnaireService.findQuestionnaireType(questionnaireId);
        model.addAttribute("questionnaireName", questionnaire.getQuestionnaireName());

        if((ResourcesStatic.GAUGE_12).equals(questionnaire.getGaugeName())){
            return "project/questionnaire/questionnairePreview/fillIn_gauge_12_preview";
        }else if((ResourcesStatic.GAUGE_7).equals(questionnaire.getGaugeName())){
            return "project/questionnaire/questionnairePreview/fillIn_gauge_7_preview";
        }else  if((ResourcesStatic.GAUGE_10).equals(questionnaire.getGaugeName())){
            return "project/questionnaire/questionnairePreview/fillIn_gauge_10_preview";
        }else {
            return "project/questionnaire/questionnairePreview/questionnaire_vertical_preview";
        }

    }

    /**
     *跳转到添加问题
     * @return
     */
    @RequestMapping("/getAddQuestion")
    public  String  getAddQuestion(String flg,String  ids,String   ascriptionType,Model model ){
        model.addAttribute("flg",flg);
        model.addAttribute("ids",ids);
        model.addAttribute("ascriptionType",ascriptionType);
        return "project/questionnaire/questionnaire/save-questionnaire-question";
    }

    /**
     * 跳转到问卷详情
     * @param questionnaireId
     * @param model
     * @return
     */
    @RequestMapping("/getQuestionnaireDetail")
    public String getQuestionnaireDetail(String questionnaireId,Model model){
        Questionnaire questionnaire=questionnaireService.findById(questionnaireId);
        //查询量表相关的标签
        List<QuestionnaireTag> questionnaireTagList= questionnaireTagService.findTagForQuestionnaire(questionnaireId);
        //相关联的场景
        List<Scene> sceneList= sceneService.findSceneForQuestionnaire(questionnaireId);
        //问题因子
        List<QuestionnaireQuestionFactor>  questionnaireQuestionFactorList= questionnaireQuestionFactorMapper.findQuestionnaireQuestionFactor(questionnaireId);
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
            if(questionnaire.getSort() != null && questionnaire.getSort() != ""){
                String[] temp =   questionnaire.getSort().split(",");
                questionnaireQuestionIds = new ArrayList<>();
                for (String d : temp){
                    questionnaireQuestionIds.add(d);
                }
                model.addAttribute("ids",questionnaireQuestionIds);
            }else{
                model.addAttribute("ids",questionnaireQuestionIds);

            }

            List<Integer> newIdIndex = new ArrayList<>();
            for(int i = 0; i < questionnaireQuestionIds.size(); i++){
                for(int y = 0; y < newQuestion.size(); y++){
                    if(questionnaireQuestionIds.get(i).equals(newQuestion.get(y))){
                        newIdIndex.add(i);
                    }
                }
            }
            model.addAttribute("newIdIndex",newIdIndex);
            model.addAttribute("sortedQuestionIds",questionnaireQuestionIds);
        }else {
            //不存在量表，只有新加问题的id
            model.addAttribute("newQuestion",questionnaireQuestionIds);
            model.addAttribute("gaugeQuestionIds","");
        }
        model.addAttribute("questionnaire",questionnaire);
        model.addAttribute("questionnaireTagList",questionnaireTagList);
        model.addAttribute("questionnaireQuestionFactorList",questionnaireQuestionFactorList);
        model.addAttribute("sceneList",sceneList);
        return "project/questionnaire/questionnaire/detail-questionnaire";
    }

    //跳转到问卷统计页面
    @RequestMapping("/questionnaireAnalysis")
    public String questionnaireAnalysis(){
        return "project/questionnaire/statisticalAnalysis/questionnaireAnalysis";
    }
}
