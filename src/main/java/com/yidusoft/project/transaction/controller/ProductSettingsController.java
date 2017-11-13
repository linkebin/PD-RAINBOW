package com.yidusoft.project.transaction.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.ProductSettings;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import com.yidusoft.project.transaction.service.ProductSettingsService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
@RestController
@RequestMapping("/product/settings")
public class ProductSettingsController {
    @Resource
    private ProductSettingsService productSettingsService;

    @Resource
    private OrderOnlineService orderOnlineService;

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
        ProductSettings product = JSON.parseObject(productJson, ProductSettings.class);
        product.setId(UUID.randomUUID().toString());
        product.setProductCode(CodeHelper.getCode("P"));
        product.setCreator(Security.getUser().getUserName());
        product.setCreateTime(new Date());
        product.setDeleted(0);
        product.setPromotionsId("");
        product.setPromotionsName("");
        productSettingsService.save(product);
        return ResultGenerator.genSuccessResult();
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
        String arr[] = ids.split(",");
        for (String str : arr) {
            ProductSettings product = productSettingsService.findById(str);
            product.setDeleted(1);
            productSettingsService.update(product);
            orderOnlineService.updateOrderOnline(str);
            //更新权限
//            shiroService.updatePermission();
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改套餐
     *
     * @param productJson
     * @return
     */
    @PostMapping("/update")
    public Result update(String productJson) {
        ProductSettings product = JSON.parseObject(productJson, ProductSettings.class);
        productSettingsService.update(product);
        return ResultGenerator.genSuccessResult(product);
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
