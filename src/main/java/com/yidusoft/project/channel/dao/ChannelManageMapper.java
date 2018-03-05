package com.yidusoft.project.channel.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.channel.domain.ChannelManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChannelManageMapper extends Mapper<ChannelManage> {

    List<Map<String,Object>> findChannelListAndTypeAndParameter(Map<String,Object> map);

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
    List<Map<String,Object>> findChannelOrAccountCounselorListByParameter(@Param("ids")List<String> ids,
                                                                          @Param("map")Map<String,Object> map);  /**
     * 查询渠道和账号下的咨询师集合
     * @param map
     * @return
     */
    List<Map<String,Object>> listByAccountCounselorForChannel(@Param("map")Map<String,Object> map);

    /**
     * 查找未通过认证的咨询师
     * @param map
     * @return
     */
    List<Map<String,Object>> findNotCertificationUser(Map<String, Object> map);


    /**
     * 查询渠道商下面所有的咨询师
     * @param map
     * @return
     */
    List<Map<String,Object>> findConsultantForChannel(Map<String,Object> map);

    /**
     * 查询所有的渠道商
     * @return
     */
    List<ChannelManage> findChannelManageAll();

    /**
     * 查询市区的人数
     * @param  map
     * @return
     */
    List<Map<String,Object>> findChannelForCity(Map<String,Object> map);

    /***
     * 查询某时间段增加的人数
     * @param map
     * @return
     */
    List<Map<String,Object>>  findChannelForTime(Map<String,Object> map);

    /**
     * 查询市区的咨询师人数
     * @param maps
     * @return
     */
    List<Map<String,Object>> findCustomerForCity(Map<String, Object> maps);
}