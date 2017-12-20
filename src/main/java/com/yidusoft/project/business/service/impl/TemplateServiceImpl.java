package com.yidusoft.project.business.service.impl;

import com.yidusoft.project.business.dao.TemplateMapper;
import com.yidusoft.project.business.domain.Template;
import com.yidusoft.project.business.service.TemplateService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/12/12.
 */
@Service
@Transactional
public class TemplateServiceImpl extends AbstractService<Template> implements TemplateService {
    @Resource
    private TemplateMapper templateMapper;

    @Override
    public List<Template> findTemplateByUserId(String userId) {
        return templateMapper.findTemplateByUserId(userId);
    }
}
