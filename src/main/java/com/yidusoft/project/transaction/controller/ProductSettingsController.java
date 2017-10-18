package com.yidusoft.project.transaction.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.ProductSettings;
import com.yidusoft.project.transaction.service.ProductSettingsService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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
     * @return
     */
    @GetMapping("/list")
    public Map<String,Object> list(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<ProductSettings> list = new ArrayList<>();
        list=productSettingsService.getProductAll();
        map.put("Rows",list);
        map.put("Total",list.size());
        return map;
    }

    /**
     * 数据分页
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
     * @param productJson
     * @return
     */
    @PostMapping("/add")
    public Result add(String productJson) {
        ProductSettings product = JSON.parseObject(productJson,ProductSettings.class);
        product.setId(UUID.randomUUID().toString());
        product.setProductCode(CodeHelper.getCode("P"));
        product.setCreator(Security.getUser().getUserName());
        product.setCreateTime(new Date());
        product.setDeleted(0);
        productSettingsService.save(product);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 套餐批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteBacth")
    @ResponseBody
    public Result deleteBacth(String  ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            ProductSettings product = productSettingsService.findById(str);
            product.setDeleted(1);
            productSettingsService.update(product);
            //更新权限
//            shiroService.updatePermission();
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改套餐
     * @param productJson
     * @return
     */
    @PostMapping("/update")
    public Result update(String productJson) {
        ProductSettings product = JSON.parseObject(productJson,ProductSettings.class);
        ProductSettings product1 = productSettingsService.findById(product.getId());
        product1.setProductTotal(product.getProductTotal());
        product1.setProductPrice(product.getProductPrice());
        productSettingsService.update(product1);
        return ResultGenerator.genSuccessResult(product1);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        productSettingsService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        ProductSettings productSettings = productSettingsService.findById(id);
        return ResultGenerator.genSuccessResult(productSettings);
    }

}
