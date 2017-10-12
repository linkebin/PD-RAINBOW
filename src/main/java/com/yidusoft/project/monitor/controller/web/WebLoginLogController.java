package com.yidusoft.project.monitor.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.monitor.domain.LoginLog;
import com.yidusoft.project.monitor.service.LoginLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@Controller
@RequestMapping("web/login/log")
public class WebLoginLogController {

    @RequestMapping("/loginloglist")
    public String loginloglist(){

        return "project/system/loginlog/loginlog-list";
    }
}
