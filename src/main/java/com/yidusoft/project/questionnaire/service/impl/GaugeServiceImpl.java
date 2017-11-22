package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.dao.GaugeMapper;
import com.yidusoft.project.questionnaire.dao.GaugeQuestionFactorMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionFactorMapper;
import com.yidusoft.project.questionnaire.dao.QuestionnaireQuestionMapper;
import com.yidusoft.project.questionnaire.domain.*;
import com.yidusoft.project.questionnaire.service.*;
import com.yidusoft.core.AbstractService;

import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class GaugeServiceImpl extends AbstractService<Gauge> implements GaugeService {
    @Resource
    private GaugeMapper gaugeMapper;

    @Resource
    private GaugeQuestionFactorMapper gaugeQuestionFactorMapper;


    @Resource
    private GaugeTagMiddleService gaugeTagMiddleService;
    @Resource
    private GaugeSceneService gaugeSceneService;
    @Resource
    private QuestionnaireTagService questionnaireTagService;

    @Resource
    private QuestionnaireQuestionMapper questionnaireQuestionMapper;

    @Resource
    private SceneService sceneService;

    @Resource
    private QuestionnaireTypeService questionnaireTypeService;

    //分页条件查询量表
    @Override
    public List<Gauge> gaugeListByPage(Gauge gauge) {
        return gaugeMapper.gaugeListByPage(gauge);
    }

    // 添加量表
    @Override
    public Result addGauge(Gauge gauge, String questionStr,String tagId,String sceneId) {
        try {
            //添加量表
            String gaugeId= UUID.randomUUID().toString();
            gauge.setId(gaugeId);
            gauge.setCreator(Security.getUser().getUserName());
            gauge.setCreateTime(new Date());
            gauge.setDeleted(0);
            gauge.setGaugeCode(CodeHelper.getCode("LB"));
            gaugeMapper.insert(gauge);
            //添加问题
            String[] questionId= questionStr.split(",");
            for(int i=0;i<questionId.length;i++){
                GaugeQuestionFactor gaugeQuestionFactor=new GaugeQuestionFactor();
                gaugeQuestionFactor.setId(UUID.randomUUID().toString());
                gaugeQuestionFactor.setGaugeId(gaugeId);
                gaugeQuestionFactor.setQuestionId(questionId[i]);
                gaugeQuestionFactorMapper.insert(gaugeQuestionFactor);
            }
            //添加标签
            gaugeTagMiddleService.addGaugeTagMiddle(tagId,gaugeId);
            //添加场景
            gaugeSceneService.addGaugeScene(sceneId,gaugeId);
        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }



        return ResultGenerator.genSuccessResult();
    }
    //修改量表
    @Override
    public Result updateGauge(Gauge gauge, String questionStr) {
        try {
            gaugeMapper.updateByPrimaryKey(gauge);
            List<GaugeQuestionFactor> list=gaugeQuestionFactorMapper.findGaugeQuestionFactor(gauge.getId());
            for(GaugeQuestionFactor gaugeQuestionFactor : list){
                gaugeQuestionFactorMapper.delete(gaugeQuestionFactor);
            }
            //添加问题
            String[] questionId= questionStr.split(",");
            for(int i=0;i<questionId.length;i++){
                GaugeQuestionFactor gaugeQuestionFactor=new GaugeQuestionFactor();
                gaugeQuestionFactor.setId(UUID.randomUUID().toString());
                gaugeQuestionFactor.setGaugeId(gauge.getId());
                gaugeQuestionFactor.setQuestionId(questionId[i]);
                gaugeQuestionFactorMapper.insert(gaugeQuestionFactor);
            }
        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }
        return ResultGenerator.genSuccessResult();
    }
    //查询所有的量表
    @Override
    public List<Gauge> findGaugeAll() {
        return gaugeMapper.findGaugeAll();
    }

    //查询量表 相关的 标签   场景
    @Override
    public Result getGaugeInfo(String id) {
        Gauge gauge=this.findById(id);
        List<GaugeQuestionFactor> gaugeQuestionList=gaugeQuestionFactorMapper.findGaugeQuestionFactor(id);
        //查询量表相关的标签
        List<QuestionnaireTag> questionnaireTagList= questionnaireTagService.findTagForGauge( id);
        //相关联的场景
        List<Scene> sceneList= sceneService.findSceneForGauge(id);
        Map<String,Object> map=new HashMap<>();
        map.put("gauge",gauge);
        map.put("gaugeQuestionList",gaugeQuestionList);
        map.put("questionnaireTagList",questionnaireTagList);
        map.put("sceneList",sceneList);

        return ResultGenerator.genSuccessResult(map);
    }

    public void changeType(String str,QuestionnaireQuestion question){
        if ("评分单选".equals(str)){
            question.setQuestionType(3);
            question.setAnswer("");
        }
        if ("单选".equals(str)){
            question.setQuestionType(2);
        }
        if ("多选".equals(str)){
            question.setQuestionType(2);
        }
        if ("收集单选".equals(str)){
            question.setQuestionType(4);
            question.setAnswer("");
        }
        if ("收集多选".equals(str)){
            question.setQuestionType(5);
            question.setAnswer("");
        }
    }

    @Override
    public Result excelImportAdd(List<ArrayList<String>> lb, List<ArrayList<String>> wt) {

        QuestionnaireType  questionnaireType = questionnaireTypeService.findBy("questionnaireTypeName",lb.get(0).get(1));

        Gauge gauge = new Gauge(UUID.randomUUID().toString(),CodeHelper.getCode("LB"),questionnaireType.getId()
                ,lb.get(0).get(0));
        gauge.setCreateTime(new Date());
        gauge.setCreator(Security.getUser().getUserName());
        gauge.setDeleted(0);
        gauge.setGaugeState(0);

//        List<QuestionnaireQuestion> questions = new ArrayList<QuestionnaireQuestion>();
//        List<GaugeQuestionFactor> gaugeQuestionFactors = new ArrayList<GaugeQuestionFactor>();
        this.save(gauge);
        try {

            for(int i=0;i<wt.size();i++){
            QuestionnaireQuestion question = new QuestionnaireQuestion();
            question.setId(lb.get(0).get(2)+"_"+(i+1));
            question.setQuestionContent(wt.get(i).get(0));
            question.setQuestionCode(CodeHelper.getCode("WT"));
            question.setAnswer(wt.get(i).get(1));
            question.setOptionAnswer(wt.get(i).get(2));
            question.setOptionScore(wt.get(i).get(3));
                //答案类型
                changeType(wt.get(i).get(4),question);

            question.setAscriptionType(2);
            question.setAnswerSequence(1);
            question.setCreator(Security.getUser().getUserName());
            question.setDeleted(0);
            question.setCreateTime(new Date());

            GaugeQuestionFactor gaugeQuestionFactor = new GaugeQuestionFactor();
            gaugeQuestionFactor.setId(UUID.randomUUID().toString());
            gaugeQuestionFactor.setGaugeId(gauge.getId());
            gaugeQuestionFactor.setQuestionId(question.getId());

            if (!"".equals(question.getQuestionContent())){
                questionnaireQuestionMapper.insert(question);
                gaugeQuestionFactorMapper.insert(gaugeQuestionFactor);
            }
        }

        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("导入失败");
        }
        return ResultGenerator.genSuccessResult("导入成功");
    }


}
