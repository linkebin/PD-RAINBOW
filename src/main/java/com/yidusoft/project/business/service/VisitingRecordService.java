package com.yidusoft.project.business.service;


import com.yidusoft.core.Service;
import com.yidusoft.project.business.domain.VisitingRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface VisitingRecordService extends Service<VisitingRecord> {

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

    /**
     * 咨询师查询来访表和预约表集合-合并
     * @param map
     * @return
     */
    List<Map<String,Object>> findVisitorAndScheduleByParameter(Map<String,Object> map);

}
