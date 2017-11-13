package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.domain.ActiveParticipant;
import com.yidusoft.project.business.service.ActiveParticipantService;
import com.yidusoft.project.business.service.LaunchActivitiesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/11/06.
*/
@RestController
@RequestMapping("/active/participant")
public class ActiveParticipantController {
    @Resource
    private ActiveParticipantService activeParticipantService;

    @Resource
    private LaunchActivitiesService launchActivitiesService;

    /**
     * 添加活动参与人的基本信息
     * @param json
     * @return
     */
    @PostMapping("/add")
    public Result add(String json) {
        ActiveParticipant activeParticipant= JSON.parseObject(json,ActiveParticipant.class);
        activeParticipant.setId(UUID.randomUUID().toString());
        activeParticipant.setCreateTime(new Date());
        activeParticipantService.save(activeParticipant);
        return ResultGenerator.genSuccessResult(activeParticipant);
    }

    /**
     * 数据分页查询
     * @param page
     * @param size
     * @param activityId
     * @return
     */
    @PostMapping("/list")
    public Result list(Integer page, Integer size, String activityId) {
        PageHelper.startPage(page, size);
        List<ActiveParticipant> list = activeParticipantService.getParticipantInfo(activityId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获取已经填报的人数
     * @param activityId
     * @return
     */
    @PostMapping("/getTotal")
    public Result getTotal(String activityId) {
        List<ActiveParticipant> list = activeParticipantService.getParticipantInfo(activityId);
        return ResultGenerator.genSuccessResult(list.size());
    }

    @PutMapping
    public Result update(ActiveParticipant activeParticipant) {
        activeParticipantService.update(activeParticipant);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        ActiveParticipant activeParticipant = activeParticipantService.findById(id);
        return ResultGenerator.genSuccessResult(activeParticipant);
    }

}
