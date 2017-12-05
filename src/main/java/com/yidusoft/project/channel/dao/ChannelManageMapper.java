package com.yidusoft.project.channel.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.channel.domain.ChannelManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChannelManageMapper extends Mapper<ChannelManage> {

    /**
     * 根据参数查询渠道列表
     * @param channelManage
     * @return
     */
    List<ChannelManage> finndChannelByParameterList(ChannelManage channelManage);


    List<ChannelManage> finndChannelNotInIdAndParameter(@Param("ids")List<String> ids, @Param("map")Map<String,Object> map);

    /**
     * 查询渠道商下的账号
     * @return
     */
    List<Map<String,Object>> findChannelAccountListByChannelId(Map<String,Object> map);

    /**
     * 查询渠道下的用户树
     * @return
     */
    List<Map<String,Object>> findChannelAccountTree(Map<String,Object> map);


    /**
     * 查询渠道和账号下的咨询师集合
     * @param map
     * @return
     */
    List<Map<String,Object>> findChannelOrAccountCounselorListByParameter(Map<String,Object> map);

    /**
     * 查找未通过认证的咨询师
     * @param map
     * @return
     */
    List<Map<String,Object>> findNotCertificationUser(Map<String, Object> map);
}