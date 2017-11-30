package com.yidusoft.project.transaction.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.transaction.dao.OrderOnlineMapper;
import com.yidusoft.project.transaction.domain.OrderOnline;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class OrderOnlineServiceImpl extends AbstractService<OrderOnline> implements OrderOnlineService {
    @Resource
    private OrderOnlineMapper orderOnlineMapper;

    @Override
    public List<OrderOnline> getUserOderById(OrderOnline orderOnline) { return orderOnlineMapper.getUserOderById(orderOnline); }

    @Override
    public void updateOrderOnline(String productId) { orderOnlineMapper.updateOrderOnline(productId); }

    @Override
    public String getOrderState(String id) { return orderOnlineMapper.getOrderState(id); }

    @Override
    public List<Map<String, Object>> findOrderByUserId(List<String> ids,Map<String,Object> map) {
        return orderOnlineMapper.findOrderByUserId(ids,map);
    }

}
