package com.yidusoft.project.channel.service;
import com.yidusoft.project.channel.domain.ClearingManage;
import com.yidusoft.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/12/01.
 */
public interface ClearingManageService extends Service<ClearingManage> {

    void clearingOrder(List<ClearingManage> list);

}
