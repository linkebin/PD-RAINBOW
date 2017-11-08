package com.yidusoft.project.monitor.controller.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.monitor.domain.OperLog;
import com.yidusoft.project.monitor.service.OperLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@Controller
@RequestMapping("web/oper/log")
public class WebOperLogController {
    @RequestMapping("/getOperLog")
    public String getOperLog(){
        return "project/system/loginlog/operLog";
    }


}
