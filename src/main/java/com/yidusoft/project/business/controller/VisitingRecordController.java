package com.yidusoft.project.business.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.business.domain.VisitingRecord;
import com.yidusoft.project.business.service.VisitingRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/visiting/record")
public class VisitingRecordController {
    @Resource
    private VisitingRecordService visitingRecordService;

    @PostMapping
    public Result add(VisitingRecord visitingRecord) {
        visitingRecordService.save(visitingRecord);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        visitingRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(VisitingRecord visitingRecord) {
        visitingRecordService.update(visitingRecord);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        VisitingRecord visitingRecord = visitingRecordService.findById(id);
        return ResultGenerator.genSuccessResult(visitingRecord);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<VisitingRecord> list = visitingRecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
