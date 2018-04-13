package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.business.domain.Schedule;
import com.yidusoft.project.business.service.ScheduleService;
import com.yidusoft.project.system.service.BacklogService;
import com.yidusoft.utils.DateUtils;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    @PostMapping("/add")
    public Result add(String json) {
        Schedule schedule = JSON.parseObject(json,Schedule.class);
        schedule.setId(UUID.randomUUID().toString());
        schedule.setCreateTime(new Date());
        schedule.setDeleted(0);
        schedule.setConsultantId(Security.getUserId());
        schedule.setCreator(Security.getUserId());
        try {
            scheduleService.save(schedule);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("保存失败");
        }
        return ResultGenerator.genSuccessResult().setMessage("保存成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        scheduleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String json) {
        Schedule schedule = JSON.parseObject(json,Schedule.class);
        try {
            scheduleService.update(schedule);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("保存失败");
        }
        return ResultGenerator.genSuccessResult().setMessage("保存成功");
    }

    @PostMapping("/detail")
    public Result detail(String id) {
        Schedule schedule = scheduleService.findById(id);
        return ResultGenerator.genSuccessResult(schedule);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Schedule> list = scheduleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    @Resource
    private BacklogService backlogService;

    @PostMapping("/scheduleTimeOrEvent")
    public Result scheduleTimeOrEvent(String type,String json) {
         Schedule schedule = JSON.parseObject(json,Schedule.class);
         schedule.setConsultantId(Security.getUserId());

        List<Schedule> scheduleList = scheduleService.findScheduleDataTimeOrEvent(schedule);

        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("agent_id",Security.getUserId());
        map2.put("visitorTimeStr",schedule.getVisitorTimeStr());

        List<Map<String,Object>> list = backlogService.findMyBackLogList(map2);

        if (type.equals("1")) {
            Map<String,Object> map = new HashMap<String,Object>();
            for (Schedule s:scheduleList) {
                map.put(DateUtils.format(s.getVisitorTime(),DateUtils.FORMAT_SHORT),"");
            }
            for (Map<String,Object> mmm:list) {
                map.put(DateUtils.format((Date) mmm.get("create_time"),DateUtils.FORMAT_SHORT),"");
            }

            return ResultGenerator.genSuccessResult(map);
        }else {
            for (Map<String,Object> mmm:list) {
                Schedule schedule1 = new Schedule();
                schedule1.setVisitorTime((Date) mmm.get("create_time"));
                schedule1.setDescribes(mmm.get("title").toString());
                schedule1.setVisitorName("");
                schedule1.setVisitorId(mmm.get("url").toString());
                scheduleList.add(schedule1);

            }
            return ResultGenerator.genSuccessResult(scheduleList);
        }
    }

    @PostMapping("/backToday30")
    public Result backToday30(String json) {
        Schedule schedule = JSON.parseObject(json,Schedule.class);
        schedule.setConsultantId(Security.getUserId());
        List<Schedule> scheduleList = scheduleService.findScheduleToBackDay30(schedule);
        return ResultGenerator.genSuccessResult(scheduleList);
    }
}
