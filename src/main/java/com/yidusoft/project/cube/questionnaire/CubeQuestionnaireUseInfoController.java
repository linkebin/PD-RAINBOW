package com.yidusoft.project.cube.questionnaire;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.domain.Scene;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yhdj on 2017/10/25.
 */

@RestController
@RequestMapping("/cube/questionnaire/")
public class CubeQuestionnaireUseInfoController {
    @Resource
    private DataAcquisitionService dataAcquisitionService;

    @PostMapping("/findQuestionnaireUseInfoListByPage")
    @ResponseBody
    public Result questionnaireUseInfoListByPage(int page,int size){
        PageHelper.startPage(page, size);
        List<DataAcquisition> list = dataAcquisitionService.questionnaireUseInfoListByPage();
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);

    }
}
