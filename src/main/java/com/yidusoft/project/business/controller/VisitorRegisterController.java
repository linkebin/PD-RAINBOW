package com.yidusoft.project.business.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.business.domain.VisitorRegister;
import com.yidusoft.project.business.service.VisitorRegisterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/visitor/register")
public class VisitorRegisterController {
    @Resource
    private VisitorRegisterService visitorRegisterService;

    @PostMapping
    public Result add(VisitorRegister visitorRegister) {
        visitorRegisterService.save(visitorRegister);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        visitorRegisterService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(VisitorRegister visitorRegister) {
        visitorRegisterService.update(visitorRegister);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        VisitorRegister visitorRegister = visitorRegisterService.findById(id);
        return ResultGenerator.genSuccessResult(visitorRegister);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<VisitorRegister> list = visitorRegisterService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
