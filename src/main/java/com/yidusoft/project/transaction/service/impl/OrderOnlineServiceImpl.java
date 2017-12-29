package com.yidusoft.project.transaction.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.domain.Backlog;
import com.yidusoft.project.system.service.BacklogService;
import com.yidusoft.project.transaction.dao.OrderOnlineMapper;
import com.yidusoft.project.transaction.domain.OrderOnline;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class OrderOnlineServiceImpl extends AbstractService<OrderOnline> implements OrderOnlineService {
    @Resource
    private OrderOnlineMapper orderOnlineMapper;

    @Resource
    private BacklogService backlogService;

    @Override
    public void addOrderOnline(OrderOnline orderOnline) {
        this.save(orderOnline);
        //添加代办
        Backlog  backlog=new Backlog();
        backlog.setId(UUID.randomUUID().toString());
        backlog.setAgentId(Security.getUserId());
        backlog.setBacklogStatus("1");
        backlog.setObjid(orderOnline.getId());
        backlog.setTitle("订单编号:"+orderOnline.getOrderCode()+"  未支付");
        backlog.setUrl("/cube/order");
        backlog.setBacklogType("ZFDD");
        backlog.setCreateTime(new Date());
        backlogService.save(backlog);

    }

    @Override
    public List<OrderOnline> getUserOderById(OrderOnline orderOnline) { return orderOnlineMapper.getUserOderById(orderOnline); }

    @Override
    public void updateOrderOnline(String productId) { orderOnlineMapper.updateOrderOnline(productId); }

    @Override
    public OrderOnline getOrderState(String id) { return orderOnlineMapper.getOrderState(id); }

    @Override
    public List<Map<String, Object>> findOrderByUserId(List<String> ids,Map<String,Object> map) {
        return orderOnlineMapper.findOrderByUserId(ids,map);
    }

}
