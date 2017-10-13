package com.yidusoft.project.transaction.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.transaction.dao.QuestionnairePromotionsMapper;
import com.yidusoft.project.transaction.domain.QuestionnairePromotions;
import com.yidusoft.project.transaction.service.QuestionnairePromotionsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class QuestionnairePromotionsServiceImpl extends AbstractService<QuestionnairePromotions> implements QuestionnairePromotionsService {
    @Resource
    private QuestionnairePromotionsMapper questionnairePromotionsMapper;

    @Override
    public List<QuestionnairePromotions> getPromotionAll() {
        return questionnairePromotionsMapper.getPromotionAll();
    }
}
