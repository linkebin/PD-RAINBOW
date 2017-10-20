package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SelectOption;

import java.util.List;

public interface SelectOptionMapper extends Mapper<SelectOption> {

    /**
     * 根据选项分类查询选项
     * @param optionCategory
     * @return
     */
    List<SelectOption> findSelectOptionByType(String optionCategory);
}