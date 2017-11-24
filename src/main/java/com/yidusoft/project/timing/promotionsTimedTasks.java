package com.yidusoft.project.timing;

import com.yidusoft.project.questionnaire.service.QuestionnaireService;
import com.yidusoft.project.transaction.domain.QuestionnairePromotions;
import com.yidusoft.project.transaction.service.QuestionnairePromotionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ldx on 2017/11/23.
 */
@Component
public class promotionsTimedTasks {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private QuestionnairePromotionsService questionnairePromotionsService;
    @Scheduled(cron="0 */1 * * *?")
    public void timedTasks(){
        logger.info("每小时执行一次。开始……");
        //查询问卷即将要上架的 修改状态
        questionnairePromotionsService.getState();

    }
}
