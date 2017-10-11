package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SysHistory;
import com.yidusoft.project.system.service.SysHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/09/08.
*/
@RestController
@RequestMapping("/sys/history")
public class SysHistoryController {
    @Resource
    private SysHistoryService sysHistoryService;

    /**
     * 查询选过的常用人员
     * @param json
     * @return
     */
    @PostMapping("/findHistorySecUserList")
    public Result findHistorySecUserList(String json) {
        SysHistory sysHistory = JSON.parseObject(json,SysHistory.class);
        return ResultGenerator.genSuccessResult(sysHistoryService.findRecentSelectLeadingByUserIdList(sysHistory));
    }



    @PostMapping("/add")
    public Result add(String json) {
        SysHistory sysHistory = JSON.parseObject(json,SysHistory.class);
        sysHistoryService.save(sysHistory);
        return ResultGenerator.genSuccessResult(sysHistory);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        sysHistoryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(SysHistory sysHistory) {
        sysHistoryService.update(sysHistory);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        SysHistory sysHistory = sysHistoryService.findById(id);
        return ResultGenerator.genSuccessResult(sysHistory);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysHistory> list = sysHistoryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
