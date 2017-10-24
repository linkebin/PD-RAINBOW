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

    /**
     * 获取所有的活动
     * @return
     */
    @Override
    public List<QuestionnairePromotions> getPromotionAll() {
        return questionnairePromotionsMapper.getPromotionAll();
    }

    /**
     * 获取参与某个活动的所有套餐
     * @return
     */
    @Override
    public List<QuestionnairePromotions> getActivityProduct(String id) { return questionnairePromotionsMapper.getActivityProduct(id); }

    /**
     * 获取活动启用并还在活动时间范围内的活动
     * @return
     */
    @Override
    public List<QuestionnairePromotions> getPromotionState() { return questionnairePromotionsMapper.getPromotionState(); }
}
