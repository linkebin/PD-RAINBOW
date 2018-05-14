package com.yidusoft.project.channel.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.dao.ClearingManageMapper;
import com.yidusoft.project.channel.domain.AccountRule;
import com.yidusoft.project.channel.domain.ClearingManage;
import com.yidusoft.project.channel.domain.PlatformSettlement;
import com.yidusoft.project.channel.service.AccountRuleService;
import com.yidusoft.project.channel.service.ChannelRuleService;
import com.yidusoft.project.channel.service.ClearingManageService;
import com.yidusoft.project.channel.service.PlatformSettlementService;
import com.yidusoft.project.transaction.domain.OrderOnline;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

import static com.yidusoft.project.channel.controller.ChannelRuleController.parseJsonObjectStrToMap;


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

    @Resource
    private AccountRuleService accountRuleService;



    @Resource
    private ChannelRuleService channelRuleService;

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

    @Override
    public List<Map<String, Object>> findChannelAccountLineChart(List<String> ids,Map<String, Object> map) {
        return clearingManageMapper.findChannelAccountLineChart(ids,map);
    }

    @Override
    public List<Map<String, Object>> findChannelAccountLineChartNew(List<String> ids, Map<String, Object> map) {
        return clearingManageMapper.findChannelAccountLineChartNew(ids,map);
    }

    @Override
    public List<Map<String, Object>> findOrderClearingByChannelCounselorId(List<String> ids, Map<String, Object> map) {
        return clearingManageMapper.findOrderClearingByChannelCounselorId(ids,map);
    }

    @Override
    public void updateClearingManageStatus(List<String> ids) {
        for (String s :ids){
            ClearingManage clearingManage = this.findBy("orderId",s);
            clearingManage.setStatus(2);
            this.update(clearingManage);
        }
    }

    @Override
    public Result saveClearing(String listMap, String channelId) {

        List<Map<String,Object>> maps = parseJsonArrayStrToListForMaps(listMap);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("channel_id",channelId);
        List<Map<String,Object>> rules = channelRuleService.findRuleByChannel(map);
        AccountRule accountRule = accountRuleService.findBy("defaultRule",1);

        List<ClearingManage> clearingManageList = new ArrayList<ClearingManage>();

        for(Map<String,Object> m :maps){
            String sdata1 = m.get("payment_time").toString();
            ClearingManage clearingManage = new ClearingManage();
            clearingManage.setClearId(UUID.randomUUID().toString());
            clearingManage.setOrderId(m.get("ID_").toString());
            clearingManage.setChannelId(channelId);
            clearingManage.setCreateTime(new Date());
            clearingManage.setCreator("系统结算");
            clearingManage.setStatus(1);
            BigDecimal money=new BigDecimal(m.get("order_money").toString());

            boolean isIn = true;
            for(Map<String,Object> ru :rules){
                boolean f = DateUtils.isOverlap(DateUtils.stampToDate(sdata1),DateUtils.stampToDate(sdata1),
                        ru.get("start_time").toString(),ru.get("end_time").toString());
                if (f){
                    isIn = false;
                    clearingManage.setRuleId(ru.get("rule_id").toString());

                    BigDecimal rum=new BigDecimal(ru.get("rule").toString());
                    clearingManage.setBrokerage(money.multiply(rum));
                    clearingManageList.add(clearingManage);
                }
            }
            if (isIn){
                clearingManage.setRuleId(accountRule.getRuleId());
                BigDecimal rum=new BigDecimal(accountRule.getRule());
                clearingManage.setBrokerage(money.multiply(rum));
                clearingManageList.add(clearingManage);
            }
        }
        try {
            //订单详细
            this.clearingOrder(clearingManageList);
            //出账单
            this.platformSettlement();
            return ResultGenerator.genSuccessResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("结算失败");
    }

    //orgJsonUtil
    public static List<Map<String, Object>> parseJsonArrayStrToListForMaps(String jsonArrayStr) {
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        try {
            if(jsonArrayStr != null) {
                JSONArray jsonArray = new JSONArray(jsonArrayStr);
                Map<String, Object> map = null;
                for(int j=0;j<jsonArray.length();j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    map = parseJsonObjectStrToMap(jsonObject.toString());
                    if(map != null) {
                        list.add(map);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(list.size() == 0) {
            return null;
        }
        return list;
    }
    @Resource
    PlatformSettlementService platformSettlementService;
    public void  platformSettlement(){
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
            platformSettlement.setYearMonth(year+"-"+month);
            platformSettlementService.save(platformSettlement);
        }
    }
}
