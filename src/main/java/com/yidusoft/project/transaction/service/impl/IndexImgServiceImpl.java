package com.yidusoft.project.transaction.service.impl;

import com.yidusoft.project.transaction.dao.IndexImgMapper;
import com.yidusoft.project.transaction.domain.IndexImg;
import com.yidusoft.project.transaction.service.IndexImgService;
import com.yidusoft.core.AbstractService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/26.
 */
@Service
@Transactional
public class IndexImgServiceImpl extends AbstractService<IndexImg> implements IndexImgService {
    @Resource
    private IndexImgMapper indexImgMapper;

    @Override
    public List<IndexImg> getIndexImgAll() { return indexImgMapper.getIndexImgAll(); }
}
