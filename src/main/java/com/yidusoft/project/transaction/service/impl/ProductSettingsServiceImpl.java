package com.yidusoft.project.transaction.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.transaction.dao.ProductSettingsMapper;
import com.yidusoft.project.transaction.domain.ProductSettings;
import com.yidusoft.project.transaction.service.ProductSettingsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class ProductSettingsServiceImpl extends AbstractService<ProductSettings> implements ProductSettingsService {
    @Resource
    private ProductSettingsMapper productSettingsMapper;

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
}
