package com.yidusoft.project.business.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.business.dao.VisitingRecordMapper;
import com.yidusoft.project.business.domain.VisitingRecord;
import com.yidusoft.project.business.service.VisitingRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class VisitingRecordServiceImpl extends AbstractService<VisitingRecord> implements VisitingRecordService {
    @Resource
    private VisitingRecordMapper visitingRecordMapper;

}
