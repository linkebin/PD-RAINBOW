package com.yidusoft.project.business.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.business.domain.VisitingRecord;

import java.util.List;
import java.util.Map;

public interface VisitingRecordMapper extends Mapper<VisitingRecord> {

    /**
     * 根据客户id 查询客户来访时间轴
     * @param visitingRecord
     * @return
     */
    List<VisitingRecord> findVisitingRecordShaftTimeByCustomerId(VisitingRecord visitingRecord);

    /**
     * 来访管理sql
     * @param visitingRecord
     * @return
     */
    List<Map<String,Object>> findVisitingRecordByParameter(VisitingRecord visitingRecord);
}