package com.yidusoft.project.channel.service;
import com.yidusoft.project.channel.domain.ClearingManage;
import com.yidusoft.core.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/12/01.
 */
public interface ClearingManageService extends Service<ClearingManage> {

    void clearingOrder(List<ClearingManage> list);

    List<Map<String,Object>> findHasClearingByChannelCounselorId(List<String> ids,
                                                                 Map<String,Object> map);

    List<Map<String,Object>> findChannelAccountLineChart(List<String> ids,Map<String,Object> map);

    List<Map<String,Object>> findChannelAccountLineChartNew(List<String> ids,Map<String,Object> map);

    List<Map<String,Object>> findOrderClearingByChannelCounselorId(List<String> ids,
                                                                   Map<String,Object> map);

}
