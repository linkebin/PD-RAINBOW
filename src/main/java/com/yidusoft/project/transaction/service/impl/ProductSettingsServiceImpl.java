package com.yidusoft.project.transaction.service.impl;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.AbstractService;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.dao.ProductSettingsMapper;
import com.yidusoft.project.transaction.domain.ModifyTrajectory;
import com.yidusoft.project.transaction.domain.ProductSettings;
import com.yidusoft.project.transaction.service.ModifyTrajectoryService;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import com.yidusoft.project.transaction.service.ProductSettingsService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class ProductSettingsServiceImpl extends AbstractService<ProductSettings> implements ProductSettingsService {
    @Resource
    private ProductSettingsMapper productSettingsMapper;

    @Resource
    private ProductSettingsService productSettingsService;

    @Resource
    private ModifyTrajectoryService modifyTrajectoryService;

    @Resource
    private OrderOnlineService orderOnlineService;

    @Override
    public List<ProductSettings> getProductAll() {
        return productSettingsMapper.getProductAll();
    }

    @Override
    public BigDecimal getUnitPriceByTotal() {
        return productSettingsMapper.getUnitPriceByTotal();
    }

    @Override
    public List<ProductSettings> getProductByTime() {
        return productSettingsMapper.getProductByTime();
    }

    @Override
    public List<ProductSettings> getUpdateUnion(String id) {
        return productSettingsMapper.getUpdateUnion(id);
    }

    @Override
    public Result updateProduct(String json) {
        ProductSettings product = JSON.parseObject(json, ProductSettings.class);
        ProductSettings productSettings = productSettingsService.findById(product.getId());
        String content = "";
        if (!product.getProductName().equals(productSettings.getProductName())) {
            content = "套餐名:" + productSettings.getProductName() + "修改为:" + product.getProductName() + "、";
        }
        if (product.getProductTotal() != productSettings.getProductTotal()) {
            content += "数量:" + productSettings.getProductTotal() + "修改为:" + product.getProductTotal() + "、";
            DecimalFormat df = new DecimalFormat("0.0");
        }
        if (Float.parseFloat(productSettings.getProductPrice().toString()) != Float.parseFloat(productSettings.getProductPrice().toString())) {
            content += "价格:" + productSettings.getProductPrice() + "修改为:" + product.getProductPrice() + "、";
        }
        if (productSettings.getProductState() != product.getProductState()) {
            String yes="是";
            String no="否";
            if(productSettings.getProductState()==1){
                yes="否";
                no="是";
            }
            content += "是否启用:" + yes + "修改为:" + no + "、";
        }
        if(content.length()>1){
            content = content.substring(0, content.length() - 1);
        }
        ModifyTrajectory mt = new ModifyTrajectory();
        mt.setContent(content);
        mt.setId(UUID.randomUUID().toString());
        mt.setCreator(Security.getUser().getUserName());
        mt.setProductId(product.getId());
        mt.setProductName(product.getProductName());
        mt.setCreateTime(new Date());
        modifyTrajectoryService.save(mt);
        productSettingsService.update(product);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result addProduct(String json) {
        ProductSettings product = JSON.parseObject(json, ProductSettings.class);
        String content = "添加了套餐:" + product.getProductName();
        ModifyTrajectory mt = new ModifyTrajectory();
        String id = UUID.randomUUID().toString();
        mt.setContent(content);
        mt.setId(id);
        mt.setCreator(Security.getUser().getUserName());
        mt.setProductId(product.getId());
        mt.setProductName(product.getProductName());
        mt.setCreateTime(new Date());
        modifyTrajectoryService.save(mt);
        product.setId(id);
        product.setProductCode(CodeHelper.getCode("P"));
        product.setCreator(Security.getUser().getUserName());
        product.setCreateTime(new Date());
        product.setDeleted(0);
        product.setPromotionsId("");
        product.setPromotionsName("");
        productSettingsService.save(product);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result deleteBacth(String ids) {
        String arr[] = ids.split(",");
        for (String str : arr) {
            ProductSettings product = productSettingsService.findById(str);
            product.setDeleted(1);
            ModifyTrajectory mt = new ModifyTrajectory();
            mt.setContent("删除了套餐:" + product.getProductName());
            mt.setId(UUID.randomUUID().toString());
            mt.setCreator(Security.getUser().getUserName());
            mt.setProductId(product.getId());
            mt.setProductName(product.getProductName());
            mt.setCreateTime(new Date());
            modifyTrajectoryService.save(mt);
            productSettingsService.update(product);
            orderOnlineService.updateOrderOnline(str);
        }
        return ResultGenerator.genSuccessResult();
    }
}
