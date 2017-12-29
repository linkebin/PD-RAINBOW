package com.yidusoft.project.system.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.Backlog;
import com.yidusoft.project.system.service.BacklogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/12/19.
*/
@RestController
@RequestMapping("/backlog")
public class BacklogController {
    @Resource
    private BacklogService backlogService;

    @PostMapping
    public Result add(Backlog backlog) {
        backlogService.save(backlog);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        backlogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(Backlog backlog) {
        backlogService.update(backlog);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        Backlog backlog = backlogService.findById(id);
        return ResultGenerator.genSuccessResult(backlog);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Backlog> list = backlogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
