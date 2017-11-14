package com.yidusoft.project.transaction.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.ModifyTrajectory;
import com.yidusoft.project.transaction.service.ModifyTrajectoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/11/14.
*/
@RestController
@RequestMapping("/modify/trajectory")
public class ModifyTrajectoryController {
    @Resource
    private ModifyTrajectoryService modifyTrajectoryService;

    @PostMapping("/listByPage")
    public Result list(Integer page,  Integer size, String json) {
        ModifyTrajectory mt= JSON.parseObject(json,ModifyTrajectory.class);
        PageHelper.startPage(page, size);
        List<ModifyTrajectory> list = modifyTrajectoryService.getAll(mt);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping
    public Result add(ModifyTrajectory modifyTrajectory) {
        modifyTrajectoryService.save(modifyTrajectory);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        modifyTrajectoryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(ModifyTrajectory modifyTrajectory) {
        modifyTrajectoryService.update(modifyTrajectory);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        ModifyTrajectory modifyTrajectory = modifyTrajectoryService.findById(id);
        return ResultGenerator.genSuccessResult(modifyTrajectory);
    }

}
