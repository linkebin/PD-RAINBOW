package com.yidusoft.project.channel.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.channel.domain.ClearingManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClearingManageMapper extends Mapper<ClearingManage> {

    List<Map<String,Object>> findHasClearingByChannelCounselorId(@Param("ids")List<String> ids,
                                                                 @Param("map")Map<String,Object> map);

    List<Map<String,Object>> findChannelAccountLineChart(@Param("ids")List<String> ids,
                                                         @Param("map")Map<String,Object> map);

    List<Map<String,Object>> findChannelAccountLineChartNew(@Param("ids")List<String> ids,
                                                         @Param("map")Map<String,Object> map);

    List<Map<String,Object>> findOrderClearingByChannelCounselorId(@Param("ids")List<String> ids,
                                                                 @Param("map")Map<String,Object> map);

    List<Map<String,Object>> findYearMonthlyCommissions();

    List<String> findYearMonthLikeIds(String yearMonth);

    void updateBatchStatus(@Param("ids")List<String> ids);
}