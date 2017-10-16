package com.yidusoft.project.channel.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.channel.domain.ChannelManage;

import java.util.List;

public interface ChannelManageMapper extends Mapper<ChannelManage> {

    /**
     * 根据参数查询渠道列表
     * @param channelManage
     * @return
     */
    List<ChannelManage> finndChannelByParameterList(ChannelManage channelManage);

}