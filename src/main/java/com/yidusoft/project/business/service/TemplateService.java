package com.yidusoft.project.business.service;
import com.yidusoft.project.business.domain.Template;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/12/12.
 */
public interface TemplateService extends Service<Template> {
    List<Template> findTemplateByUserId(String userId);
}
