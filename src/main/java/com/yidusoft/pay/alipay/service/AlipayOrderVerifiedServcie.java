package com.yidusoft.pay.alipay.service;

import com.yidusoft.core.Service;
import com.yidusoft.pay.alipay.domain.OrderOnlineBean;

public interface AlipayOrderVerifiedServcie extends Service<OrderOnlineBean> {
    /**
     * 根据订单编号查询订单信息
     *
     * @param orderCode
     * @return
     */
    OrderOnlineBean getOrderOnlineByCode(String orderCode);
}
