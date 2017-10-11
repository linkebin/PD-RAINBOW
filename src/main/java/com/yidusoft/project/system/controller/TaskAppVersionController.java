package com.yidusoft.project.system.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.service.TaskAppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smy on 2017/9/7.
 */
@RestController
@RequestMapping("/app/version")
public class TaskAppVersionController {

    @Autowired
    private TaskAppVersionService taskAppVersionService;

    @RequestMapping("/findAppVersionList")
    public Result findAppVersionList(){
        return ResultGenerator.genSuccessResult(taskAppVersionService.findAppVersionList());
    }
}
