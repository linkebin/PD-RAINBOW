package com.yidusoft.project.channel.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.channel.domain.ChannelManage;

import java.util.List;
import java.util.Map;

public interface ChannelManageMapper extends Mapper<ChannelManage> {

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

    /**
     * 查询渠道下的账号树
     * @return
     */
    List<Map<String,Object>> findChannelAccountTree(Map<String,Object> map);


    /**
     * 查询渠道和账号下的咨询师集合
     * @param map
     * @return
     */
    List<Map<String,Object>> findChannelOrAccountCounselorListByParameter(Map<String,Object> map);

}