package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.ProductSettings;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface ProductSettingsService extends Service<ProductSettings> {
    List<ProductSettings> getProductAll();
}