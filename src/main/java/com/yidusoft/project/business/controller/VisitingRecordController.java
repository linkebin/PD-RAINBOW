package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.business.domain.Schedule;
import com.yidusoft.project.business.domain.VisitingRecord;
import com.yidusoft.project.business.domain.VisitingRecordFile;
import com.yidusoft.project.business.domain.VisitorRegister;
import com.yidusoft.project.business.service.ScheduleService;
import com.yidusoft.project.business.service.VisitingRecordFileService;
import com.yidusoft.project.business.service.VisitingRecordService;
import com.yidusoft.project.business.service.VisitorRegisterService;
import com.yidusoft.project.system.domain.Backlog;
import com.yidusoft.project.system.domain.SelectOption;
import com.yidusoft.project.system.service.BacklogService;
import com.yidusoft.project.system.service.SelectOptionService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.DateUtils;
import com.yidusoft.utils.JavaBeanUtil;
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

    @Resource
    private VisitorRegisterService visitorRegisterService;

    @PostMapping("/goalManage")
    public Result goalManage(Integer page,  Integer limit) {

        PageHelper.startPage(page, limit);
        List<SelectOption> list =selectOptionService.findSelectOptionByType("goal");
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }

    @PostMapping("/provincePrcGoalRanking")
    public Result provincePrcGoalRanking(String json) {
        Map<String,Object> parameter = JSON.parseObject(json,Map.class);
        List<SelectOption> list =selectOptionService.findSelectOptionByType("goal");

        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();

        for (SelectOption selectOption : list){
            parameter.put("option_name",selectOption.getOptionName());
            List<Map<String,Object>> types = visitingRecordService.findComeToCallGoalAreaCount(parameter);
            if (types.size()>0){
                data.add(types.get(0));
            }
        }
        return ResultGenerator.genSuccessResult(data);
    }


    @PostMapping("/comeToCallGoalAreaCount")
    public Result comeToCallGoalAreaCount(String json) {
        Map<String,Object> parameter = JSON.parseObject(json,Map.class);
        List<Map<String,Object>> provinces = visitorRegisterService.findVisitorRegisterProvince();
        List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();

        for(Map<String,Object> map : provinces){

            parameter.put("province",map.get("province").toString());
            List<Map<String,Object>> types = visitingRecordService.findComeToCallGoalAreaCount(parameter);

            if (types.size()>0){
                Map<String,Object> d = new HashMap<String,Object>();
                String s = map.get("province").toString();
                d.put("name",s.substring(0,s.length() - 1));
                int value = 0;
                String value2 = "";
                for(Map<String,Object> map2 : types){
                    value+=Integer.parseInt(map2.get("total").toString());
                    value2+=map2.get("option_name").toString()+"::"+map2.get("total").toString()+",";
                }

                d.put("value",value);
                d.put("value2",value2);
                data.add(d);
            }

        }
        return ResultGenerator.genSuccessResult(data);
    }


    @PostMapping("/goalBarChart")
    public Result clearingLineChart(String json) {
        Map<String,Object> parameter = JSON.parseObject(json,Map.class);

        List<Map<String,Object>> maps = visitingRecordService.findGoalBarChart(parameter);
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
    @Resource
    private BacklogService backlogService;


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
            VisitorRegister visitorRegister = visitorRegisterService.findById(visitingRecord.getVisitorId());
            saveDBLFJL(visitingRecord,visitorRegister);
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

    @PostMapping("/update")
    public Result update(String json) {
        VisitingRecord visitingRecord = JSON.parseObject(json,VisitingRecord.class);
        try {
            visitingRecordService.update(visitingRecord);
            String intArray3 [] = {"visitingPurpose","processLog","followUpPlan","interview","evaluate","thisTime","resolved","remarks"};
            Backlog backlog2 = backlogService.findBy("objid",visitingRecord.getId());

            VisitingRecord visitingRecord2 = visitingRecordService.findById(visitingRecord.getId());
            VisitorRegister visitorRegister = visitorRegisterService.findById(visitingRecord2.getVisitorId());

            if (JavaBeanUtil.checkFieldValueNull(visitingRecord,intArray3)){
                if (backlog2!=null){
                    backlogService.deleteById(backlog2.getId());
                }
                saveDBLFJL(visitingRecord2,visitorRegister);
            }else {
                if (backlog2!=null){
                    backlog2.setBacklogStatus("2");
                    backlogService.update(backlog2);
                }
            }

            return ResultGenerator.genSuccessResult();
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("编辑失败");
        }
    }

    public void saveDBLFJL(VisitingRecord visitingRecord,VisitorRegister visitorRegister){
        String intArray3 [] = {"visitingPurpose","processLog","followUpPlan","interview","evaluate","thisTime","resolved","remarks"};
        if (JavaBeanUtil.checkFieldValueNull(visitingRecord,intArray3)){
            Backlog backlog = new Backlog();
            backlog.setId(UUID.randomUUID().toString());
            backlog.setTitle(visitorRegister.getVisitorName() + "   "+ DateUtils.format(visitingRecord.getVisitorTime(),"yyyy-MM-dd") +"    来访记录未填写完整");
            backlog.setBacklogStatus("1");
            backlog.setAgentId(visitingRecord.getCreator());
            backlog.setBacklogType("DBLFJL");
            backlog.setCreateTime(new Date());
            backlog.setUrl("web/customer/visitorInfo?id="+visitingRecord.getVisitorId());
            backlog.setObjid(visitingRecord.getId());
            backlogService.save(backlog);
        }
    }

    @PostMapping("/detail")
    public Result detail(String id) {
        VisitingRecord visitingRecord = visitingRecordService.findById(id);

        SelectOption selectOption = selectOptionService.findById(visitingRecord.getVisitorGoal());
        visitingRecord.setVisitorGoal(selectOption.getOptionValue());

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
        List<VisitingRecord> data = new ArrayList<VisitingRecord>();
        for (VisitingRecord v : list){
            v.setDeleted(2);
            Backlog backlog = backlogService.findBy("objid",v.getId());
            if (backlog !=null && backlog.getBacklogStatus().equals("1")){
                v.setDeleted(1);
            }
            data.add(v);
        }

        PageInfo pageInfo = new PageInfo(data);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
