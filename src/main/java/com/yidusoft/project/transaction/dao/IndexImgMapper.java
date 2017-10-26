package com.yidusoft.project.transaction.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.transaction.domain.IndexImg;

import java.util.List;

public interface IndexImgMapper extends Mapper<IndexImg> {
    /**
     * 获取首页图片的列表
     * @return
     */
    List<IndexImg> getIndexImgAll();
}