package com.yidusoft.project.channel.service;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface ChannelManageService extends Service<ChannelManage> {

    /**
     * 根据参数查询渠道列表
     * @param channelManage
     * @return
     */
    List<ChannelManage> finndChannelByParameterList(ChannelManage channelManage);

    /**
     * 查询渠道商下的账号
     * @return
     */
    List<Map<String,Object>> findChannelAccountListByChannelId(Map<String,Object> map);

}
