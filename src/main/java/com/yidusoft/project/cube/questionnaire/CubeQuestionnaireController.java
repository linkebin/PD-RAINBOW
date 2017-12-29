package com.yidusoft.project.cube.questionnaire;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/cube/questionnaire/")
public class CubeQuestionnaireController {
        @Resource
        private QuestionnaireQuestionService questionnaireQuestionService;


    /**
     * 问卷提交
     * @param param
     * @param questionnaireId
     * @param userId
     * @return
     */
    @PostMapping("/submitQuestionnaire")
    @ResponseBody
    public  Result submitQuestionnaire(String param, String questionnaireId,String userId,String visitorTimes,String timeConsuming,String activityId,String userName){
        return questionnaireQuestionService.submitQuestionnaire(param,questionnaireId,userId,visitorTimes,timeConsuming,activityId,userName);
    }

    /**
     * 活动问卷提交
     * @param param
     * @param questionnaireId
     * @param userId
     * @return
     */
    @PostMapping("/subQuestionnaire")
    @ResponseBody
    public  Result subQuestionnaire(String param, String questionnaireId,String userId,String visitorTimes,String timeConsuming,String activityId,String userName){
        return questionnaireQuestionService.submitQuestionnaire(param,questionnaireId,userId,visitorTimes,timeConsuming,activityId,userName);
    }

}
