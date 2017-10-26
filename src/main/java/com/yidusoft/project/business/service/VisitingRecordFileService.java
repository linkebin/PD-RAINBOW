package com.yidusoft.project.business.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.business.domain.VisitingRecordFile;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface VisitingRecordFileService extends Service<VisitingRecordFile> {

    public List<VisitingRecordFile> findVisitingRecordFileByVisitorId(VisitingRecordFile visitingRecordFile);
}
