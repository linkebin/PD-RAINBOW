package com.yidusoft.project.transaction.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.ProductSettings;
import com.yidusoft.project.transaction.domain.QuestionnairePromotions;
import com.yidusoft.project.transaction.service.ProductSettingsService;
import com.yidusoft.project.transaction.service.QuestionnairePromotionsService;
import com.yidusoft.utils.Security;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Transactional
@RestController
@RequestMapping("/questionnaire/promotions")
public class QuestionnairePromotionsController {
    @Resource
    private QuestionnairePromotionsService questionnairePromotionsService;

    @Resource
    private ProductSettingsService productSettingsService;

    /**
     * 列表数据分页
     *
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
     * 列表数据
     */
    @GetMapping("/list")
    public Result list() {
        List<QuestionnairePromotions> list = questionnairePromotionsService.getPromotionAll();
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 获取启用的活动
     *
     * @return
     */
    @GetMapping("/getState")
    public Result getState() {
        List<QuestionnairePromotions> list = questionnairePromotionsService.getPromotionState();
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 添加活动
     * @param promotionsJson
     * @return
     */
    @PostMapping("/add")
    public Result add(String promotionsJson,String ids) {
        String id_=UUID.randomUUID().toString();
        QuestionnairePromotions promotions = JSON.parseObject(promotionsJson, QuestionnairePromotions.class);
        promotions.setId(id_);
        promotions.setCreator(Security.getUser().getUserName());
        promotions.setCreateTime(new Date());
        promotions.setDeleted(0);
        promotions.setPromotionsState(1);
        if(promotions.getPromotionsType()==1){
            promotions.setPromotionsBuySend(0);
        }else{
            BigDecimal decimal = new BigDecimal("0");
            promotions.setPromotionsDiscount(decimal);
        }
        questionnairePromotionsService.save(promotions);
        if(ids!=null && ids!=""){
            String arr[] = ids.split(",");
            for (String id : arr) {
                ProductSettings productSettings = productSettingsService.findById(id);
                productSettings.setPromotionsId(id_);
                productSettings.setPromotionsName(promotions.getPromotionsName());
                productSettingsService.update(productSettings);
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 批量删除活动
     *
     * @param ids
     * @return
     */
    @Transactional
    @PostMapping("/batchDeletion")
    public Result batchDeletion(String ids) {
        String arr[] = ids.split(",");
        for (String id : arr) {
            QuestionnairePromotions questionnairePromotions = questionnairePromotionsService.findById(id);
            questionnairePromotions.setDeleted(1);
            questionnairePromotionsService.update(questionnairePromotions);
            ProductSettings productSettings = productSettingsService.findBy("promotionsId", id);
            if (productSettings != null) {
                productSettings.setPromotionsId("");
                productSettings.setPromotionsName("");
                productSettingsService.update(productSettings);
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改活动
     *
     * @param promotionsJson
     * @return
     */
    @PostMapping("/update")
    public Result update(String promotionsJson) {
        QuestionnairePromotions questionnairePromotions = JSON.parseObject(promotionsJson, QuestionnairePromotions.class);
        List<QuestionnairePromotions> list = questionnairePromotionsService.getActivityProduct(questionnairePromotions.getId());
        for(QuestionnairePromotions qp:list){
            ProductSettings productSettings=qp.getProductSettings();
            productSettings.setPromotionsName(questionnairePromotions.getPromotionsName());
            productSettingsService.update(productSettings);
        }
        questionnairePromotionsService.update(questionnairePromotions);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改套餐
     * @param promotionsId
     * @param promotionsName
     * @param ids
     * @return
     */
    @PostMapping("/updateProduct")
    public Result updateProduct(String promotionsId,String promotionsName,String ids) {
        if(ids!=null && ids!=""){
            String arr[] = ids.split(",");
            for (String id : arr) {
                ProductSettings productSettings = productSettingsService.findById(id);
                productSettings.setPromotionsId(promotionsId);
                productSettings.setPromotionsName(promotionsName);
                productSettingsService.update(productSettings);
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取活动和套餐
     *
     * @return
     */
    @GetMapping("/getProductAndQuestionPro")
    public Result getProductAndQuestionPro() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<QuestionnairePromotions> questionList = questionnairePromotionsService.getPromotionAll();
        List<ProductSettings> productList = productSettingsService.getProductAll();
        map.put("questionList", questionList);
        map.put("productList", productList);
        return ResultGenerator.genSuccessResult(map);
    }

    /**
     * 获取参与某个活动的所有套餐
     *
     * @param id
     * @return
     */
    @PostMapping("/getActivityProduct")
    public Result getActivityProduct(String id) {
        List<QuestionnairePromotions> list = questionnairePromotionsService.getActivityProduct(id);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 添加促销活动的图片
     *
     * @param id
     * @param imgPath
     * @return
     */
    @PostMapping("/addImage")
    public Result addImage(String id, String imgPath) {
        QuestionnairePromotions questionnairePromotions = questionnairePromotionsService.findById(id);
        questionnairePromotions.setImagePath(imgPath);
        questionnairePromotionsService.update(questionnairePromotions);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取单个活动
     *
     * @param id
     * @return
     */
    @PostMapping("/getOne")
    public Result getOne(String id) {
        QuestionnairePromotions questionnairePromotions = questionnairePromotionsService.findById(id);
        return ResultGenerator.genSuccessResult(questionnairePromotions);
    }

    /**
     * 启用促销
     * @param id
     * @return
     */
    @PostMapping("/updateState")
    public Result updateState(String id,Integer state) {
        QuestionnairePromotions questionnairePromotions = questionnairePromotionsService.findById(id);
        questionnairePromotions.setPromotionsState(state);
        questionnairePromotionsService.update(questionnairePromotions);
        if(state==3){
            productSettingsService.updateProductPromotions(questionnairePromotions.getId());
        }
        return ResultGenerator.genSuccessResult(questionnairePromotions);
    }
}
