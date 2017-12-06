package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SelectOption;
import com.yidusoft.project.system.service.SelectOptionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/10/12.
*/
@RestController
@RequestMapping("/select/option")
public class SelectOptionController {
    @Resource
    private SelectOptionService selectOptionService;

    @PostMapping("/add")
    public Result add(String json) {
        SelectOption selectOption = JSON.parseObject(json,SelectOption.class);
        selectOption.setOptionId(CodeHelper.getCode("lfmd"));
        selectOption.setCreator(Security.getUser().getUserName());
        selectOption.setDeleted(0);
        selectOption.setCreateTime(new Date());
        selectOption.setOptionCategory("goal");

        selectOptionService.save(selectOption);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(String  id) {
        SelectOption selectOption = selectOptionService.findById(id);
        selectOption.setDeleted(1);
        selectOptionService.update(selectOption);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String json) {
        SelectOption selectOption = JSON.parseObject(json,SelectOption.class);
        selectOptionService.update(selectOption);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        SelectOption selectOption = selectOptionService.findById(id);
        return ResultGenerator.genSuccessResult(selectOption);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SelectOption> list = selectOptionService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
