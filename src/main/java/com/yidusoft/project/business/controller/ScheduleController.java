package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.business.domain.Schedule;
import com.yidusoft.project.business.service.ScheduleService;
import com.yidusoft.utils.DateUtils;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    @PostMapping
    public Result add(Schedule schedule) {
        scheduleService.save(schedule);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        scheduleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(Schedule schedule) {
        scheduleService.update(schedule);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
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

    @PostMapping("/scheduleTimeOrEvent")
    public Result scheduleTimeOrEvent(String type,String json) {
         Schedule schedule = JSON.parseObject(json,Schedule.class);
         schedule.setConsultantId(Security.getUserId());

        List<Schedule> scheduleList = scheduleService.findScheduleDataTimeOrEvent(schedule);
        if (type.equals("1")) {
            Map<String,Object> map = new HashMap<String,Object>();
            for (Schedule s:scheduleList) {
                map.put(DateUtils.format(s.getVisitorTime(),DateUtils.FORMAT_SHORT),"预约");
            }
            return ResultGenerator.genSuccessResult(map);
        }else {
            return ResultGenerator.genSuccessResult(scheduleList);
        }
    }
}
