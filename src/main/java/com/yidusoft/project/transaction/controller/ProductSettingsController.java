package com.yidusoft.project.transaction.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.ProductSettings;
import com.yidusoft.project.transaction.service.ProductSettingsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
@RestController
@RequestMapping("/product/settings")
public class ProductSettingsController {
    @Resource
    private ProductSettingsService productSettingsService;


    /**
     * 获取套餐列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        List<ProductSettings> list = new ArrayList<>();
        list = productSettingsService.getProductAll();
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 数据分页
     *
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/listByPage")
    public Result listByPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<ProductSettings> list = productSettingsService.getProductAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 设置套餐
     *
     * @param productJson
     * @return
     */
    @PostMapping("/add")
    public Result add(String productJson) {
        return productSettingsService.addProduct(productJson);
    }

    /**
     * 套餐批量删除
     *
     * @param ids
     * @return
     */
    @Transactional
    @PostMapping("/deleteBacth")
    public Result deleteBacth(String ids) {
        return productSettingsService.deleteBacth(ids);
    }

    /**
     * 修改套餐
     *
     * @param productJson
     * @return
     */
    @PostMapping("/update")
    public Result update(String productJson) {
        return productSettingsService.updateProduct(productJson);
    }

    /**
     * 获取问卷单价
     *
     * @return
     */
    @GetMapping("/getUnitPriceByTotal")
    public Result getUnitPriceByTotal() {
        BigDecimal price = productSettingsService.getUnitPriceByTotal();
        return ResultGenerator.genSuccessResult(price);
    }

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    @PostMapping("/detail")
    public Result detail(String id) {
        ProductSettings productSettings = productSettingsService.findById(id);
        return ResultGenerator.genSuccessResult(productSettings);
    }

    /**
     * 获取没参与活动和活动已过期的套餐
     *
     * @return
     */
    @PostMapping("/listPage")
    public Result listPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<ProductSettings> list = productSettingsService.getProductByTime();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    /**
     * 获取参与该活动并启用的套餐
     * @return
     */
    @PostMapping("/updateListPage")
    public Result updateListPage(int page, int size, String id) {
        PageHelper.startPage(page, size);
        List<ProductSettings> list = productSettingsService.getUpdateUnion(id);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 删除活动的套餐
     */
    @PostMapping("/updateProduct")
    public Result updateProduct(String id){
       ProductSettings productSettings = productSettingsService.findById(id);
        productSettings.setPromotionsId("");
        productSettings.setPromotionsName("");
        productSettingsService.update(productSettings);
        return ResultGenerator.genSuccessResult();
    }
}
