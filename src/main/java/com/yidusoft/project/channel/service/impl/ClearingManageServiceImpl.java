package com.yidusoft.project.channel.service.impl;

import com.yidusoft.project.channel.dao.ClearingManageMapper;
import com.yidusoft.project.channel.domain.ClearingManage;
import com.yidusoft.project.channel.service.ClearingManageService;
import com.yidusoft.core.AbstractService;

import com.yidusoft.project.transaction.domain.OrderOnline;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/12/01.
 */
@Service
@Transactional
public class ClearingManageServiceImpl extends AbstractService<ClearingManage> implements ClearingManageService {
    @Resource
    private ClearingManageMapper clearingManageMapper;

    @Resource
    private OrderOnlineService orderOnlineService;

    @Override
    public void clearingOrder(List<ClearingManage> list) {
        for (ClearingManage c : list){
            OrderOnline orderOnline = new OrderOnline();
            orderOnline.setId(c.getOrderId());
            orderOnline.setClearingStatus(2);
            orderOnlineService.update(orderOnline);
            clearingManageMapper.insert(c);
        }
    }

    @Override
    public List<Map<String, Object>> findHasClearingByChannelCounselorId(List<String> ids, Map<String, Object> map) {
        return clearingManageMapper.findHasClearingByChannelCounselorId(ids,map);
    }
}
