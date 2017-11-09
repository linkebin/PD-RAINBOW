package com.yidusoft.pay.alipay.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.pay.alipay.dao.AlipayOrderVerifiedMapper;
import com.yidusoft.pay.alipay.domain.OrderOnlineBean;
import com.yidusoft.pay.alipay.service.AlipayOrderVerifiedServcie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @desc
 * @Author zhanglong
 * @Email zhanglong@yidusoft.net
 * @create 2017-11-03 11:10
 **/
@Service
public class AlipayOrderVerifiedServcieImpl extends AbstractService<OrderOnlineBean> implements AlipayOrderVerifiedServcie {

    @Resource
    AlipayOrderVerifiedMapper alipayOrderVerifiedMapper;

    /**
     * 根据订单编号获取订单信息
     *
     * @param orderCode
     * @return
     */
    @Override
    public OrderOnlineBean getOrderOnlineByCode(String orderCode) {
        return alipayOrderVerifiedMapper.getOrderOnlineByCode(orderCode);
    }
}
