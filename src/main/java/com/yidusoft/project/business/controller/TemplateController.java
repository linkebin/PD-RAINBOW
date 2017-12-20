package com.yidusoft.project.business.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.business.domain.Template;
import com.yidusoft.project.business.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/12/12.
*/
@RestController
@RequestMapping("/template")
public class TemplateController {
    @Resource
    private TemplateService templateService;

    /**
     * 添加模板
     * @param json
     * @return
     */
    @PostMapping("/add")
    public Result add(String json) {
        Template template = JSON.parseObject(json,Template.class);
        template.setId(UUID.randomUUID().toString());
        template.setCreateTime(new Date());
        template.setUserId(Security.getUserId());
        templateService.save(template);
        try {
            return ResultGenerator.genSuccessResult();
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("");
        }
    }

    /**
     * 获取用户收藏的模板
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        List<Template> list = templateService.findTemplateByUserId(Security.getUserId());
        return ResultGenerator.genSuccessResult(list);
    }

    @PutMapping
    public Result update(Template template) {
        templateService.update(template);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail")
    public Result detail(String id) {
        Template template = templateService.findById(id);
        return ResultGenerator.genSuccessResult(template);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Template> list = templateService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
