package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.QuestionnairePromotions;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface QuestionnairePromotionsService extends Service<QuestionnairePromotions> {
    List<QuestionnairePromotions> getPromotionAll();
}