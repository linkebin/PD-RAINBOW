package com.yidusoft.project.transaction.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.transaction.domain.IndexImg;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/10/26.
 */
public interface IndexImgService extends Service<IndexImg> {
    /**
     * 获取首页图片的列表
     * @return
     */
    List<IndexImg> getIndexImgAll();
}
