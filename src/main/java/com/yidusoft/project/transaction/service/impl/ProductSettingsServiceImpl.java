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

    /**
     * 修改套餐
     * @param json
     * @return
     */
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
        if (productSettings.getProductPrice().toString().equals(productSettings.getProductPrice().toString())) {
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
        addTrajectory(content,UUID.randomUUID().toString(),product.getId(),product.getProductName());
        productSettingsService.update(product);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 添加套餐
     * @param json
     * @return
     */
    @Override
    public Result addProduct(String json) {
        ProductSettings product = JSON.parseObject(json, ProductSettings.class);
        String content = "添加了套餐:" + product.getProductName();
        ModifyTrajectory mt = new ModifyTrajectory();
        String id = UUID.randomUUID().toString();
        //添加修改轨迹
        addTrajectory(content,id,product.getId(),product.getProductName());
        product.setId(id);
        product.setProductCode(CodeHelper.getCode("P"));
        product.setCreator(Security.getUser().getUserName());
        product.setCreateTime(new Date());
        product.setDeleted(0);
        product.setPromotionsId("");
        product.setPromotionsName("");
        if(product.getProductType()==1){
            product.setTimeLimit(0);
        }else{
            product.setProductTotal(0);
        }
        productSettingsService.save(product);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 批量删除套餐
     * @param ids
     * @return
     */
    @Override
    public Result deleteBacth(String ids) {
        String arr[] = ids.split(",");
        for (String str : arr) {
            ProductSettings product = productSettingsService.findById(str);
            product.setDeleted(1);
            String content = "删除了套餐:" + product.getProductName();
            //添加修改轨迹
            addTrajectory(content,UUID.randomUUID().toString(),product.getId(),product.getProductName());
            productSettingsService.update(product);
            orderOnlineService.updateOrderOnline(str);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 添加套餐修改轨迹
     * @param content
     * @param uuId
     * @param productId
     * @param name
     */
    @Override
    public void addTrajectory(String content,String uuId,String productId,String name) {
        ModifyTrajectory mt = new ModifyTrajectory();
        mt.setContent(content);
        mt.setId(uuId);
        mt.setCreator(Security.getUser().getUserName());
        mt.setProductId(productId);
        mt.setProductName(name);
        mt.setCreateTime(new Date());
        modifyTrajectoryService.save(mt);
    }

    @Override
    public void updateProductPromotions(String promotionsId) { productSettingsMapper.updateProductPromotions(promotionsId); }
}
