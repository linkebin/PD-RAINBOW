package com.yidusoft.project.transaction.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.transaction.domain.QuestionnairePromotions;

import java.util.List;

public interface QuestionnairePromotionsMapper extends Mapper<QuestionnairePromotions> {
    List<QuestionnairePromotions> getPromotionAll();
}