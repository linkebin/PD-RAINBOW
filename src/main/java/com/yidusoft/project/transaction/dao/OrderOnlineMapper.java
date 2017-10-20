package com.yidusoft.project.transaction.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.transaction.domain.OrderOnline;

import java.util.List;

public interface OrderOnlineMapper extends Mapper<OrderOnline> {

    List<OrderOnline> getUserOderById(OrderOnline orderOnline);
}