package com.yidusoft.project.questionnaire.service.impl;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.dao.QuestionnaireTagMiddleMapper;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTagMiddle;
import com.yidusoft.project.questionnaire.service.QuestionnaireTagMiddleService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnaireTagMiddleServiceImpl extends AbstractService<QuestionnaireTagMiddle> implements QuestionnaireTagMiddleService {
    @Resource
    private QuestionnaireTagMiddleMapper questionnaireTagMiddleMapper;

    //绑定标签与问卷
    @Override
    public Result addQuestionnaireTagMiddle(String tagId, String questionnaireId) {

        try {
            String [] tagArray=tagId.split(",");
            for(int i=0; i<tagArray.length;i++){
                if(!"".equals(tagArray[i])){
                QuestionnaireTagMiddle questionnaireTagMiddle=new QuestionnaireTagMiddle();
                questionnaireTagMiddle.setId(UUID.randomUUID().toString());
                questionnaireTagMiddle.setQuestionnaireId(questionnaireId);
                questionnaireTagMiddle.setQuestionnaireTagId(tagArray[i]);
                questionnaireTagMiddleMapper.insert(questionnaireTagMiddle);
                }
            }
        }catch (Exception e){
            return ResultGenerator.genFailResult("操作失败");
        }
        return ResultGenerator.genSuccessResult();
    }
    //删除绑定的标签问卷
    @Override
    public Result deleteQuestionnaireTagMiddle(QuestionnaireTagMiddle questionnaireTagMiddle) {
         try{
             QuestionnaireTagMiddle questionnaireTagMiddle1= questionnaireTagMiddleMapper.deleteQuestionnaireTagMiddle(questionnaireTagMiddle);
             questionnaireTagMiddleMapper.delete(questionnaireTagMiddle1);
         }catch (Exception e){
             return ResultGenerator.genFailResult("操作失败");
         }
        return  ResultGenerator.genSuccessResult();
    }
}
