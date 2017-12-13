package com.yidusoft.project.transaction.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.transaction.domain.ProductSettings;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductSettingsMapper extends Mapper<ProductSettings> {

    List<ProductSettings> getProductAll();

    BigDecimal getUnitPriceByTotal();

    List<ProductSettings> getProductByTime(ProductSettings productSettings);

    List<ProductSettings> getUpdateUnion(String id);

    void updateProductPromotions(String promotionsId);

    List<ProductSettings> findProductTotal();

    List<ProductSettings> findProductByOrder();

    List<ProductSettings> findProductByTime(Map map);
}