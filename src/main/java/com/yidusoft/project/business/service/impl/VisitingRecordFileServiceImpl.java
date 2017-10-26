package com.yidusoft.project.business.service.impl;
import com.yidusoft.core.AbstractService;
import com.yidusoft.project.business.dao.VisitingRecordFileMapper;
import com.yidusoft.project.business.domain.VisitingRecordFile;
import com.yidusoft.project.business.service.VisitingRecordFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class VisitingRecordFileServiceImpl extends AbstractService<VisitingRecordFile> implements VisitingRecordFileService {
    @Resource
    private VisitingRecordFileMapper visitingRecordFileMapper;

    @Override
    public List<VisitingRecordFile> findVisitingRecordFileByVisitorId(VisitingRecordFile visitingRecordFile) {
        return visitingRecordFileMapper.findVisitingRecordFileByVisitorId(visitingRecordFile);
    }
}
