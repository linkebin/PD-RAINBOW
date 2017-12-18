package com.yidusoft.project.channel.service.impl;

import com.github.pagehelper.PageHelper;
import com.yidusoft.project.channel.dao.ClearingManageMapper;
import com.yidusoft.project.channel.dao.PlatformSettlementMapper;
import com.yidusoft.project.channel.domain.PlatformSettlement;
import com.yidusoft.project.channel.service.ClearingManageService;
import com.yidusoft.project.channel.service.PlatformSettlementService;
import com.yidusoft.core.AbstractService;

import com.yidusoft.utils.CodeHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2017/12/13.
 */
@Service
@Transactional
public class PlatformSettlementServiceImpl extends AbstractService<PlatformSettlement> implements PlatformSettlementService {
    @Resource
    private PlatformSettlementMapper platformSettlementMapper;

    @Resource
    private ClearingManageMapper clearingManageMapper;

    @Override
    public List<Map<String, Object>> findPlatformSettlementList(Map<String,Object> map) {
        List<Map<String,Object>> yearMonthList = clearingManageMapper.findYearMonthlyCommissions();
        for (Map<String,Object> m : yearMonthList) {
            List<String> uids = clearingManageMapper.findYearMonthLikeIds(m.get("create_time").toString());
            if (uids.size()>0){
                clearingManageMapper.updateBatchStatus(uids);
            }
            String year = m.get("create_time").toString().split("-")[0];

            String month = m.get("create_time").toString().split("-")[1];

            PlatformSettlement platformSettlement = new PlatformSettlement();
            platformSettlement.setId(UUID.randomUUID().toString());
            platformSettlement.setCode(CodeHelper.getCode("ZD"));
            platformSettlement.setCreateTime(new Date());
            platformSettlement.setYear(year);

            int M = Integer.parseInt(month)-1;
            platformSettlement.setMonth(String.valueOf(M));
            if (M<10){
                platformSettlement.setMonth("0"+M);
            }

            platformSettlement.setShouldBe(new BigDecimal(m.get("brokerage").toString()));
            platformSettlement.setClosed(new BigDecimal("0.0"));
            platformSettlement.setUnliquidated(new BigDecimal(m.get("brokerage").toString()));
            platformSettlement.setStatus("1");
            this.save(platformSettlement);
        }
        PageHelper.startPage(1, 10);
//        PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String,Object>> data = platformSettlementMapper.findPlatformSettlementList(map);

        return data;
    }

    @Override
    public List<Map<String, Object>> findPlatformSettlementChannelInfo(Map<String, Object> map) {
        return platformSettlementMapper.findPlatformSettlementChannelInfo(map);
    }

    @Override
    public List<Map<String, Object>> findAreaAndChannelOrderList(List<String> ids, Map<String, Object> map) {
        return platformSettlementMapper.findAreaAndChannelOrderList(ids,map);
    }

    @Override
    public List<Map<String, Object>> findAreaAndChannelOrderZYJ(List<String> ids, Map<String, Object> map) {
        return platformSettlementMapper.findAreaAndChannelOrderZYJ(ids,map);
    }
}
