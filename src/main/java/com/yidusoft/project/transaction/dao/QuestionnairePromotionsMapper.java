package com.yidusoft.project.transaction.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.transaction.domain.QuestionnairePromotions;

import java.util.List;

public interface QuestionnairePromotionsMapper extends Mapper<QuestionnairePromotions> {
    /**
     * 获取所有的活动
     * @return
     */
    List<QuestionnairePromotions> getPromotionAll();

    /**
     * 获取参与某个活动的所有套餐
     * @return
     */
    List<QuestionnairePromotions> getActivityProduct(String id);

    /**
     * 获取活动启用并还在活动时间范围内的活动
     * @return
     */
    List<QuestionnairePromotions> getPromotionState();
}