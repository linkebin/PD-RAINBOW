package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.business.domain.VisitorRegister;
import com.yidusoft.project.business.service.VisitorRegisterService;
import com.yidusoft.utils.CodeHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/visitor/register")
public class VisitorRegisterController {
    @Resource
    private VisitorRegisterService visitorRegisterService;

    @PostMapping("/add")
    public Result add(String json) {

        VisitorRegister visitorRegister = JSON.parseObject(json,VisitorRegister.class);
        visitorRegister.setId(UUID.randomUUID().toString());
        visitorRegister.setVisitorCode(CodeHelper.getCode("DJ"));
        visitorRegister.setCreateTime(new Date());
        visitorRegister.setDeleted(0);

        try {
            visitorRegisterService.save(visitorRegister);
        }catch (Exception e){
            return ResultGenerator.genFailResult("登记失败");
        }
        return ResultGenerator.genSuccessResult().setMessage("登记成功");
    }

    @PostMapping("/update")
    public Result update(String json) {
        VisitorRegister visitorRegister = JSON.parseObject(json,VisitorRegister.class);

        try {
            visitorRegisterService.deleteById(visitorRegister.getId());
            visitorRegisterService.save(visitorRegister);
        }catch (Exception e){
            return ResultGenerator.genFailResult("保存失败");
        }
        return ResultGenerator.genSuccessResult().setMessage("保存成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        visitorRegisterService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }



    @PostMapping("/detail")
    public Result detail(String id) {
        VisitorRegister visitorRegister = visitorRegisterService.findById(id);
        return ResultGenerator.genSuccessResult(visitorRegister);
    }

    @PostMapping("/listByparameter")
    public Result listByparameter( int page, int pagesize,String json) {
        VisitorRegister visitorRegister = JSON.parseObject(json,VisitorRegister.class);

        PageHelper.startPage(page, pagesize);
        List<VisitorRegister> list = visitorRegisterService.findViitorByCounselorId(visitorRegister);

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
