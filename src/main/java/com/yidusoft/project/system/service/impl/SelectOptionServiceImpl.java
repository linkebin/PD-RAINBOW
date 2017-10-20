package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.SelectOptionMapper;
import com.yidusoft.project.system.domain.SelectOption;
import com.yidusoft.project.system.service.SelectOptionService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/12.
 */
@Service
@Transactional
public class SelectOptionServiceImpl extends AbstractService<SelectOption> implements SelectOptionService {
    @Resource
    private SelectOptionMapper selectOptionMapper;


    @Override
    public List<SelectOption> findSelectOptionByType(String optionCategory) {
        return selectOptionMapper.findSelectOptionByType(optionCategory);
    }
}
