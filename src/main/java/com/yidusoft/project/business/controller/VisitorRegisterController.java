package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.project.business.domain.VisitorRegister;
import com.yidusoft.project.business.service.VisitorRegisterService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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

        VisitorRegister visitorRegister = JSON.parseObject(json, VisitorRegister.class);
        visitorRegister.setId(UUID.randomUUID().toString());
        visitorRegister.setVisitorCode(CodeHelper.getCode("DJ"));
        visitorRegister.setCreateTime(new Date());
        visitorRegister.setDeleted(0);
        try {
            visitorRegisterService.save(visitorRegister);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("登记失败");
        }
        return ResultGenerator.genSuccessResult().setMessage("登记成功");
    }

    @PostMapping("/update")
    public Result update(String json) {
        VisitorRegister visitorRegister = JSON.parseObject(json, VisitorRegister.class);

        try {
            visitorRegisterService.deleteById(visitorRegister.getId());
            visitorRegister.setDeleted(0);
            visitorRegister.setCreator(Security.getUserId());
            visitorRegisterService.save(visitorRegister);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("保存失败");
        }
        return ResultGenerator.genSuccessResult().setMessage("保存成功");
    }

    @PostMapping("/delete")
    public Result delete(String id) {
        VisitorRegister visitorRegister = visitorRegisterService.findById(id);
        visitorRegister.setDeleted(1);
        try {
            visitorRegisterService.update(visitorRegister);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件删除失败");
        }
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/detail")
    public Result detail(String id) {
        VisitorRegister visitorRegister = visitorRegisterService.findById(id);
        return ResultGenerator.genSuccessResult(visitorRegister);
    }

    @PostMapping("/listByparameter")
    public Result listByparameter(int page, int pagesize, String json) {
        VisitorRegister visitorRegister = JSON.parseObject(json, VisitorRegister.class);

        PageHelper.startPage(page, pagesize);
        List<VisitorRegister> list = visitorRegisterService.findViitorByCounselorId(visitorRegister);

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/listByparameterTime")
    public Result listByparameterTime(int page, int pagesize, String json) {
        VisitorRegister visitorRegister = JSON.parseObject(json, VisitorRegister.class);

        PageHelper.startPage(page, pagesize);
        List<Map<String, Object>> list = visitorRegisterService.findViitorByCounselorIdSortTime(visitorRegister);

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获取客户信息
     *
     * @return
     */
    @PostMapping("/customerCount")
    public Result count(String occupation) {
        Map<String,String> map = new HashMap<>();
        map.put("creator",Security.getUserId());
        map.put("occupation",occupation);
        List<VisitorRegister> list = visitorRegisterService.findVitorByCreator(map);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/getCount")
    public Result getCount(Date startTime, Date endTime, String sex, String maritalStatus, String belief,String ageGroupsIds,String goalIds,String types)  {
        return visitorRegisterService.acquisitionOfStatisticalAnalysis(
                startTime, endTime, sex, maritalStatus, belief,ageGroupsIds,goalIds,types);
    }

    /*@PostMapping("/getQuestionCount")
    public Result getQuestionCount(Date startTime, Date endTime, String sex, String maritalStatus,String belief,String questionName,String type)  {
        return visitorRegisterService.getQuestion(startTime, endTime, sex, maritalStatus, belief,questionName,type);
    }*/
}
