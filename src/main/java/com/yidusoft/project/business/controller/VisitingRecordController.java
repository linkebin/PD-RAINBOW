package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.business.domain.Schedule;
import com.yidusoft.project.business.domain.VisitingRecord;
import com.yidusoft.project.business.domain.VisitingRecordFile;
import com.yidusoft.project.business.service.ScheduleService;
import com.yidusoft.project.business.service.VisitingRecordFileService;
import com.yidusoft.project.business.service.VisitingRecordService;
import com.yidusoft.project.system.domain.SelectOption;
import com.yidusoft.project.system.service.SelectOptionService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/visiting/record")
public class VisitingRecordController {
    @Resource
    private VisitingRecordService visitingRecordService;

    @Autowired
    private VisitingRecordFileService visitingRecordFileService;

    @Autowired
    private SelectOptionService selectOptionService;

    @PostMapping("/goalManage")
    public Result goalManage(Integer page,  Integer limit) {

        PageHelper.startPage(page, limit);
        List<SelectOption> list =selectOptionService.findSelectOptionByType("goal");
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }

    @PostMapping("/goalBarChart")
    public Result clearingLineChart() {

        List<Map<String,Object>> maps = visitingRecordService.findGoalBarChart();
        int startSize = maps.size();
        List<SelectOption> list =selectOptionService.findSelectOptionByType("goal");
        for (SelectOption selectOption : list){
            boolean f = true;
            if (startSize>0) {
                for (Map<String, Object> m : maps) {
                    if (m.get("visitor_goal").toString().equals(selectOption.getOptionId())) {
                        f = false;
                        break;
                    }
                }
            }
            if (f){
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("option_name",selectOption.getOptionValue());
                map.put("total",0);
                maps.add(map);
            }
        }
        return ResultGenerator.genSuccessResult(maps);
    }


    @PostMapping("/add")
    public Result add(String json) {
        VisitingRecord visitingRecord = JSON.parseObject(json,VisitingRecord.class);
        visitingRecord.setCreateTime(new Date());
        visitingRecord.setId(UUID.randomUUID().toString());
        visitingRecord.setDeleted(0);
        visitingRecord.setCreator(Security.getUserId());
        visitingRecord.setRecordCode(CodeHelper.getCode("LF"));
        try {
            visitingRecordService.save(visitingRecord);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("保存失败！");
        }

        return ResultGenerator.genSuccessResult().setMessage("保存成功！");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        visitingRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(VisitingRecord visitingRecord) {
        visitingRecordService.update(visitingRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(String id) {
        VisitingRecord visitingRecord = visitingRecordService.findById(id);
        VisitingRecordFile visitingRecordFile = new VisitingRecordFile();
        visitingRecordFile.setCreator(Security.getUserId());

        visitingRecordFile.setRecordId(visitingRecord.getId());
        List<VisitingRecordFile> visitingRecordFiles = visitingRecordFileService.findVisitingRecordFileByVisitorId(visitingRecordFile);
        visitingRecord.setVisitingRecordFileList(visitingRecordFiles);
        return ResultGenerator.genSuccessResult(visitingRecord);
    }

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/listByparameter")
    public Result listByparameter(int page, int pagesize,String json) {
        Map<String,Object> map = JSON.parseObject(json,Map.class);
        map.put("consultantId",Security.getUserId());
        PageHelper.startPage(page, pagesize);
        List<Map<String,Object>> list = visitingRecordService.findVisitorAndScheduleByParameter(map);

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/shaftTime")
    public Result shaftTime(String json){
        VisitingRecord visitingRecord = JSON.parseObject(json,VisitingRecord.class);

        List<VisitingRecord> list = visitingRecordService.findVisitingRecordShaftTimeByCustomerId(visitingRecord);

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
