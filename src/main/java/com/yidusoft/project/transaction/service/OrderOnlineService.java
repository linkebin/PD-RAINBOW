package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.OrderOnline;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface OrderOnlineService extends Service<OrderOnline> {
    List<OrderOnline> getUserOderById(OrderOnline orderOnline);
}
