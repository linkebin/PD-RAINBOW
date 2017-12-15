package com.yidusoft.project.channel.service;
import com.yidusoft.project.channel.domain.PlatformSettlement;
import com.yidusoft.core.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/12/13.
 */
public interface PlatformSettlementService extends Service<PlatformSettlement> {

    List<Map<String,Object>> findPlatformSettlementList(Map<String,Object> map);

    List<Map<String,Object>> findPlatformSettlementChannelInfo(Map<String,Object> map);

    //区域和渠道订单列表
    List<Map<String,Object>> findAreaAndChannelOrderList(@Param("ids")List<String> ids,
                                                         @Param("map")Map<String,Object> map);

}
