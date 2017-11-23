package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Result;
import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.QuestionnairePromotions;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnairePromotionsService extends Service<QuestionnairePromotions> {
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

    /**
     * 获取设计中的活动
     * @return
     */
    Result getState();
}
