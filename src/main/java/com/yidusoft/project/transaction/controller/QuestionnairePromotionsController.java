package com.yidusoft.project.transaction.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.QuestionnairePromotions;
import com.yidusoft.project.transaction.service.QuestionnairePromotionsService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/questionnaire/promotions")
public class QuestionnairePromotionsController {
    @Resource
    private QuestionnairePromotionsService questionnairePromotionsService;
    /**
     * 获取活动列表
     * @return
     */
    @GetMapping("/list")
    public Map<String,Object> list(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<QuestionnairePromotions> list = new ArrayList<>();
        list=questionnairePromotionsService.getPromotionAll();
        map.put("Rows",list);
        map.put("Total",list.size());
        return map;
    }

    /**
     * 列表数据分页
     * @param page
     * @param size
     */
    @PostMapping("/listByPage")
    public Result listByPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<QuestionnairePromotions> list = questionnairePromotionsService.getPromotionAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 添加活动
     * @param promotionsJson
     * @return
     */
    @PostMapping("/add")
    public Result add(String promotionsJson) {
        QuestionnairePromotions promotions = JSON.parseObject(promotionsJson,QuestionnairePromotions.class);
        promotions.setId(UUID.randomUUID().toString());;
        promotions.setCreator(Security.getUser().getUserName());
        promotions.setCreateTime(new Date());
        promotions.setDeleted(0);
        questionnairePromotionsService.save(promotions);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除活动
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result delete(String  id) {
        QuestionnairePromotions questionnairePromotions=questionnairePromotionsService.findById(id);
        questionnairePromotions.setDeleted(1);
        questionnairePromotionsService.update(questionnairePromotions);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String promotionsJson) {
        QuestionnairePromotions questionnairePromotions=JSON.parseObject(promotionsJson,QuestionnairePromotions.class);
        questionnairePromotionsService.update(questionnairePromotions);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        QuestionnairePromotions questionnairePromotions = questionnairePromotionsService.findById(id);
        return ResultGenerator.genSuccessResult(questionnairePromotions);
    }

}
