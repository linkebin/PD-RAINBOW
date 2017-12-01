package com.yidusoft.project.business.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.business.dao.VisitingRecordMapper;
import com.yidusoft.project.business.domain.VisitingRecord;
import com.yidusoft.project.business.service.VisitingRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class VisitingRecordServiceImpl extends AbstractService<VisitingRecord> implements VisitingRecordService {
    @Resource
    private VisitingRecordMapper visitingRecordMapper;

    @Override
    public List<VisitingRecord> findVisitingRecordShaftTimeByCustomerId(VisitingRecord visitingRecord) {
        return visitingRecordMapper.findVisitingRecordShaftTimeByCustomerId(visitingRecord);
    }

    @Override
    public List<Map<String,Object>> findVisitingRecordByParameter(VisitingRecord visitingRecord) {
        return visitingRecordMapper.findVisitingRecordByParameter(visitingRecord);
    }

    @Override
    public List<Map<String, Object>> findVisitorAndScheduleByParameter(Map<String, Object> map) {
        return visitingRecordMapper.findVisitorAndScheduleByParameter(map);
    }
    /**
     * 查询来访者的来访的总次数
     * @param userId
     * @return
     */
    @Override
    public int getVisitingTotal(String userId) {
        return visitingRecordMapper.getVisitingTotal(userId);
    }
}
