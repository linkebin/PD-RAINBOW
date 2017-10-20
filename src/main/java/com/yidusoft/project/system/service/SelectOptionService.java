package com.yidusoft.project.system.service;
import com.yidusoft.project.system.domain.SelectOption;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/12.
 */
public interface SelectOptionService extends Service<SelectOption> {

    /**
     * 根据选项分类查询选项
     * @param optionCategory
     * @return
     */
    List<SelectOption> findSelectOptionByType(String optionCategory);

}
