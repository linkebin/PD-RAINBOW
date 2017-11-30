package com.yidusoft.project.transaction.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.transaction.domain.OrderOnline;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderOnlineMapper extends Mapper<OrderOnline> {
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
    String getOrderState(String id);

    /**
     * 修改订单的状态为2，即已支付
     * @param id
     */
    void updateState(String id);

    //查询渠道全部咨询师的订单
    List<Map<String,Object>> findOrderByUserId(@Param("ids")List<String> ids,
                                               @Param("map")Map<String,Object> map);
}