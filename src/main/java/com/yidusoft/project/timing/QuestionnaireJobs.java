package com.yidusoft.project.timing;

import com.yidusoft.project.questionnaire.service.QuestionnaireService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zcb on 2017/10/31.
 */
@Component
public class QuestionnaireJobs {


    @Resource
    private QuestionnaireService questionnaireService;
    @Scheduled(cron="0 0 5 * * ? ")
    public void cronJob(){
      //查询问卷即将要上架的 修改状态
        questionnaireService.setQuestionnaireState();

    }

}