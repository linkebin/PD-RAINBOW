package com.yidusoft.project.transaction.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.transaction.domain.ProductSettings;

import java.util.List;

public interface ProductSettingsMapper extends Mapper<ProductSettings> {

    List<ProductSettings> getProductAll();

    Integer getUnitPriceByTotal();
}