package com.yidusoft.pay.alipay.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.pay.alipay.domain.OrderOnlineBean;

/**
 * @desc
 * @Author zhanglong
 * @Email zhanglong@yidusoft.net
 * @create 2017-11-03 11:35
 **/

public interface AlipayOrderVerifiedMapper extends Mapper<OrderOnlineBean> {
    /**
     * 根据订单编号获取订单信息
     *
     * @param orderCode
     * @return
     */
    OrderOnlineBean getOrderOnlineByCode(String orderCode);
}
