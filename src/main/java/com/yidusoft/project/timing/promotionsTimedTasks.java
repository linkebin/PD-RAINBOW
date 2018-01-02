package com.yidusoft.project.timing;

import com.alibaba.fastjson.JSON;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.channel.service.ClearingManageService;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import com.yidusoft.project.transaction.service.QuestionnairePromotionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ldx on 2017/11/23.
 */
@Component
public class promotionsTimedTasks {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private QuestionnairePromotionsService questionnairePromotionsService;
    @Resource
    private ChannelManageService channelManageService;

    @Resource
    private ClearingManageService clearingManageService;

    @Resource
    private OrderOnlineService orderOnlineService;


    @Scheduled(cron="0 0 */1 * * ?")
    public void timedTasks(){
        logger.info("每小时执行一次。开始……");
        //查询问卷即将要上架的 修改状态
        questionnairePromotionsService.getState();

    }



    //每月1号结算订单佣金
    @Scheduled(cron = "0 0 2 1 * ?")
    public void orderAndSettlement(){
        logger.info("结算佣金开始");
        List<ChannelManage> channelManages = channelManageService.findChannelManageAll();
        for (ChannelManage s : channelManages){
            logger.info("当前结算渠道商"+s.getChannelName());
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("channel_id",s.getId());
            map.put("type",2);
            List<String> ids = getChannelConsultIds(map);
            List<Map<String,Object>> list = null;
            if (ids.size()>0){
                list = orderOnlineService.findOrderByUserId(ids,map);
            }
            if (list.size()>0){
                clearingManageService.saveClearing(JSON.toJSONString(list),s.getId());
            }
        }
        logger.info("结算佣金结束");
    }



    public List<String> getChannelConsultIds(Map<String,Object> map){
        List<String> ids = new ArrayList<String>();
        List<Map<String,Object>> list = channelManageService.findChannelOrAccountCounselorListByParameter(null,map);
        for (Map<String,Object> m : list){
            ids.add(m.get("ID_").toString());
        }
        return ids;
    }
}
