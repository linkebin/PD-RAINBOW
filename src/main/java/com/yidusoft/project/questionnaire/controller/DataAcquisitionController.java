package com.yidusoft.project.questionnaire.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Security;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/data/acquisition")
public class DataAcquisitionController {
    @Resource
    private DataAcquisitionService dataAcquisitionService;

    /**
     * 根据日期查询来访者使用的问卷
     * @param dataAcquisition
     * @return
     */
    @PostMapping("/findQuestionnaireForVisitor")
    public Result  findQuestionnaireForVisitor(DataAcquisition dataAcquisition) {
        List<DataAcquisition> list=dataAcquisitionService.findQuestionnaireForVisitor(dataAcquisition);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 来访者的问卷统计分析
     * @param userId
     * @return
     */
    @PostMapping("/getDataAcquisitionForVisitingCount")
    public Result  findQuestionnaireForVisitor(String userId) {
        return dataAcquisitionService.getDataAcquisitionForVisitingCount(userId);
    }
    /**
     * 查询来访者的填写结果
     * @param dataAcquisition
     * @return
     */
    @PostMapping("/findDataAcquistionForVisitor")
    public  Result  findDataAcquistionForVisitor(DataAcquisition dataAcquisition){
        //来访者问卷使用记录
        List<DataAcquisition> dataAcquisitionList= dataAcquisitionService.findDataAcquistionForVisitor(dataAcquisition);
        return ResultGenerator.genSuccessResult(dataAcquisitionList);
    }



    @PostMapping
    public Result add(DataAcquisition dataAcquisition) {
        dataAcquisitionService.save(dataAcquisition);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        dataAcquisitionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(DataAcquisition dataAcquisition) {
        dataAcquisitionService.update(dataAcquisition);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        DataAcquisition dataAcquisition = dataAcquisitionService.findById(id);
        return ResultGenerator.genSuccessResult(dataAcquisition);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DataAcquisition> list = dataAcquisitionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获取活动的填报人数
     * @param activityId
     * @return
     */
    @PostMapping("/findCount")
    public Result findCountByActivityId(String activityId){
        Integer total=dataAcquisitionService.findCountByActivityId(activityId);
        return ResultGenerator.genSuccessResult(total);
    }
}
