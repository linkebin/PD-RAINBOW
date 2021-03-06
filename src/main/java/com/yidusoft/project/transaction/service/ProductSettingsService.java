package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Result;
import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.ProductSettings;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface ProductSettingsService extends Service<ProductSettings> {
    List<ProductSettings> getProductAll();

    BigDecimal getUnitPriceByTotal();

    List<ProductSettings> getProductByTime(ProductSettings productSettings);

    List<ProductSettings> getUpdateUnion(String id);

    Result updateProduct(String json);

    Result addProduct(String json);

    Result deleteBacth(String ids);

    void addTrajectory(String content,String uuId,String productId,String name);

    void updateProductPromotions(String promotionsId);

    List<ProductSettings> findProductTotal();

    List<ProductSettings> findProductByOrder();

    List<ProductSettings> findProductByTime(Map map);
}
