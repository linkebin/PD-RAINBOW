package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.OrderOnline;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface OrderOnlineService extends Service<OrderOnline> {
    /**
     * 添加订单
     * @param orderOnline
     */
     void addOrderOnline (OrderOnline orderOnline);

    /**
     * 根据订单状态获取用户的订单
     * @param orderOnline
     * @return
     */
    List<OrderOnline> getUserOderById(OrderOnline orderOnline);

    /**
     * 套餐删除时修改与套餐相关的订单的状态
     * @param productId
     */
    void updateOrderOnline(String productId);

    /**
     * 获取订单的状态
     * @param id
     * @return
     */
    OrderOnline getOrderState(String id);

    //查询渠道全部咨询师的订单
    List<Map<String,Object>> findOrderByUserId(List<String> ids,Map<String,Object> map);
}
