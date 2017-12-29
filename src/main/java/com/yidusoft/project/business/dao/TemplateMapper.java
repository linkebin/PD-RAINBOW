package com.yidusoft.project.business.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.business.domain.Template;

import java.util.List;

public interface TemplateMapper extends Mapper<Template> {
    List<Template> findTemplateByUserId(String userId);
}