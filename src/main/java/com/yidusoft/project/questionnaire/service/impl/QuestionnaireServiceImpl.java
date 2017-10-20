package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.dao.QuestionnaireMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionFactorMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireSceneMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireTagMiddleMapper;
import com.yidusoft.project.questionnaire.domain.*;
import com.yidusoft.project.questionnaire.service.*;
import com.yidusoft.core.AbstractService;

import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin.util.UIUtil;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireServiceImpl extends AbstractService<Questionnaire> implements QuestionnaireService {
    @Resource
    private QuestionnaireMapper questionnaireMapper;
    @Resource
    private QuestionnaireTagMiddleService questionnaireTagMiddleService;
    @Resource
    private QuestionnaireSceneService questionnaireSceneService;

    @Resource
    private QuestionnaireQuestionFactorMapper questionnaireQuestionFactorMapper;
    @Resource
    private QuestionnaireTagService questionnaireTagService;
    @Resource
    private SceneService sceneService;
    //分页条件查询相关的问卷信息
    @Override
    public List<Questionnaire> questionnaireListByPage(Questionnaire questionnaire) {
        return questionnaireMapper.questionnaireListByPage(questionnaire);
    }

    //添加问卷
    @Override
    public Result addQuestionnaire(Questionnaire questionnaire, String questionStr, String tagId, String sceneId) {
        try{
            //问卷
            String questionnaireId= UUID.randomUUID().toString();
            questionnaire.setId(questionnaireId);
            questionnaire.setQuestionnaireCode(CodeHelper.getCode("WJ"));
            questionnaire.setQuestionnaireState(1);
            questionnaire.setCreator(Security.getUser().getUserName());
            questionnaire.setCreateTime(new Date());
            questionnaire.setDeleted(0);
            this.save(questionnaire);
            //问题
            String [] questionArray=questionStr.split(",");
            for(int i=0; i<questionArray.length;i++){
                QuestionnaireQuestionFactor questionnaireQuestionFactor=new  QuestionnaireQuestionFactor();
                questionnaireQuestionFactor.setId(UUID.randomUUID().toString());
                questionnaireQuestionFactor.setQuestionId(questionArray[i]);
                questionnaireQuestionFactor.setQuestionnaireId(questionnaireId);
                questionnaireQuestionFactorMapper.insert(questionnaireQuestionFactor);
            }
            //标签
            questionnaireTagMiddleService.addQuestionnaireTagMiddle(tagId,questionnaireId);
            //场景
            questionnaireSceneService.addQuestionnaireScene(sceneId,questionnaireId);

        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }

        return ResultGenerator.genSuccessResult();
    }
    //查询问卷 相关的 标签   场景
    @Override
    public Result getQuestionnaireInfo(String id) {
        Questionnaire questionnaire=this.findById(id);
        //查询量表相关的标签
        List<QuestionnaireTag> questionnaireTagList= questionnaireTagService.findTagForQuestionnaire( id);
        //相关联的场景
        List<Scene> sceneList= sceneService.findSceneForQuestionnaire(id);
        //问题因子
        List<QuestionnaireQuestionFactor>  questionnaireQuestionFactorList= questionnaireQuestionFactorMapper.findQuestionnaireQuestionFactor(id);
        Map<String,Object> map=new HashMap<>();
        map.put("questionnaire",questionnaire);
        map.put("questionnaireQuestionFactorList",questionnaireQuestionFactorList);
        map.put("questionnaireTagList",questionnaireTagList);
        map.put("sceneList",sceneList);
        return ResultGenerator.genSuccessResult(map);
    }

    //修改问卷信息
    @Override
    public Result updateQuestionnaire(Questionnaire questionnaire, String questionStr) {
        try{
            questionnaire.setUpdateTime(new Date());
            questionnaire.setModifier(Security.getUser().getUserName());
            this.update(questionnaire);
            List<QuestionnaireQuestionFactor> questionnaireQuestionFactors=questionnaireQuestionFactorMapper.findQuestionnaireQuestionFactor(questionnaire.getId());
            //删除所有的问卷问题因子
            for(QuestionnaireQuestionFactor questionFactor : questionnaireQuestionFactors){
                questionnaireQuestionFactorMapper.delete(questionFactor);
            }
            //添加修改后的问题问卷因子
          String [] questionArray=  questionStr.split(",");
           for(int i=0;i<questionArray.length;i++){
               if(!"".equals(questionArray[i])){
                   QuestionnaireQuestionFactor questionFactor=new QuestionnaireQuestionFactor();
                   questionFactor.setId(UUID.randomUUID().toString());
                   questionFactor.setQuestionId(questionArray[i]);
                   questionFactor.setQuestionnaireId(questionnaire.getId());
                   questionnaireQuestionFactorMapper.insert(questionFactor);
               }
         }
        }catch (Exception e){

        }

        return null;
    }
}
