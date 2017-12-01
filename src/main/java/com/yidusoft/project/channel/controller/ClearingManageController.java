package com.yidusoft.project.channel.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.ClearingManage;
import com.yidusoft.project.channel.service.ClearingManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/12/01.
*/
@RestController
@RequestMapping("/clearing/manage")
public class ClearingManageController {
    @Resource
    private ClearingManageService clearingManageService;

    @PostMapping
    public Result add(ClearingManage clearingManage) {
        clearingManageService.save(clearingManage);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        clearingManageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(ClearingManage clearingManage) {
        clearingManageService.update(clearingManage);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        ClearingManage clearingManage = clearingManageService.findById(id);
        return ResultGenerator.genSuccessResult(clearingManage);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ClearingManage> list = clearingManageService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
