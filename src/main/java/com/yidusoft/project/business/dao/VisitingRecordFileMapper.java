package com.yidusoft.project.business.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.business.domain.VisitingRecordFile;

import java.util.List;

public interface VisitingRecordFileMapper extends Mapper<VisitingRecordFile> {

    public List<VisitingRecordFile> findVisitingRecordFileByVisitorId(VisitingRecordFile visitingRecordFile);
}