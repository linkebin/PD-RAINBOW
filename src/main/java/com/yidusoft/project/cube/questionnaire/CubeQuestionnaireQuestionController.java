package com.yidusoft.project.cube.questionnaire;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireQuestion;
import com.yidusoft.project.questionnaire.service.QuestionnaireQuestionService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/cube/questionnaire/question")
public class CubeQuestionnaireQuestionController {
        @Resource
        private QuestionnaireQuestionService questionnaireQuestionService;

    /**
     * 查询问卷相关的问题
     * @param id
     * @return
     */
    @PostMapping("/findQuestionForQuestionnaire")
    @ResponseBody
    public  Result findQuestionForQuestionnaire(String id){
        List<QuestionnaireQuestion> questionnaireQuestionList= questionnaireQuestionService.findQuestionForQuestionnaire(id);
        return ResultGenerator.genSuccessResult(questionnaireQuestionList);
    }


}
