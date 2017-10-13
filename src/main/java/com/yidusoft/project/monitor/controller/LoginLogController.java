package com.yidusoft.project.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.monitor.domain.LoginLog;
import com.yidusoft.project.monitor.service.LoginLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.system.domain.SecMenu;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/login/log")
public class LoginLogController {
    @Resource
    private LoginLogService loginLogService;


    @PostMapping("/listByparameter")
    public Result listByparameter(int page,int pagesize,String json) {
        LoginLog loginLog = JSON.parseObject(json,LoginLog.class);

        PageHelper.startPage(page,pagesize);

        PageInfo pageInfo = new PageInfo(loginLogService.findLoginLogByParameter(loginLog));
        return   ResultGenerator.genSuccessResult(pageInfo);
    }



    @PostMapping
    public Result add(LoginLog loginLog) {
        loginLogService.save(loginLog);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        loginLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(LoginLog loginLog) {
        loginLogService.update(loginLog);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        LoginLog loginLog = loginLogService.findById(id);
        return ResultGenerator.genSuccessResult(loginLog);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<LoginLog> list = loginLogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
