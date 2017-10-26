package com.yidusoft.project.business.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.business.domain.VisitingRecordFile;
import com.yidusoft.project.business.service.VisitingRecordFileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/visiting/record/file")
public class VisitingRecordFileController {
    @Resource
    private VisitingRecordFileService visitingRecordFileService;

    @PostMapping
    public Result add(VisitingRecordFile visitingRecordFile) {
        visitingRecordFileService.save(visitingRecordFile);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(String  id) {
        VisitingRecordFile visitingRecordFile = visitingRecordFileService.findById(id);
        visitingRecordFile.setDeleted(1);
        try {
            visitingRecordFileService.update(visitingRecordFile);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件删除失败");
        }
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(VisitingRecordFile visitingRecordFile) {
        visitingRecordFileService.update(visitingRecordFile);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        VisitingRecordFile visitingRecordFile = visitingRecordFileService.findById(id);
        return ResultGenerator.genSuccessResult(visitingRecordFile);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<VisitingRecordFile> list = visitingRecordFileService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
