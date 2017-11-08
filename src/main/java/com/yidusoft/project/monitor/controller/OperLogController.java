package com.yidusoft.project.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.monitor.domain.LoginLog;
import com.yidusoft.project.monitor.domain.OperLog;
import com.yidusoft.project.monitor.service.OperLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/oper/log")
public class OperLogController {
    @Resource
    private OperLogService operLogService;

    /**
     * 分页条件查询所有的行为记录
     * @param params
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("/operLogListByPage")
    @ResponseBody
    public Result operLogListByPage(String params, Integer page,  Integer limit) {
        OperLog operLog= JSON.parseObject(params,OperLog.class);
        PageHelper.startPage(page, limit);
        List<OperLog> list =operLogService.operLogListByPage(operLog);
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo.getList()).setCount(pageInfo.getTotal()).setCode(0);
    }


}
