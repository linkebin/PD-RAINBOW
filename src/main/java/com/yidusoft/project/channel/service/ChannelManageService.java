package com.yidusoft.project.channel.service;
import com.yidusoft.core.Result;
import com.yidusoft.core.Service;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.system.domain.SecUser;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
public interface ChannelManageService extends Service<ChannelManage> {

    Result signChannel(SecUser secUser,ChannelManage channelManage) throws Exception;

    List<Map<String,Object>> findChannelListAndTypeAndParameter(Map<String,Object> map);
    //区域渠道树
    List<Map<String,Object>> findAreaChannelTree();

    /**
     * 根据参数查询渠道列表
     * @param channelManage
     * @return
     */
    List<ChannelManage> finndChannelByParameterList(ChannelManage channelManage);

    /**
     * 结算规则查询可用的渠道商列表
     * @param map
     * @return
     */
    List<ChannelManage> finndChannelNotInIdAndParameter(List<String> ids,Map<String,Object> map);



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
    List<Map<String,Object>> findChannelOrAccountCounselorListByParameter(List<String> ids,
                                                                          Map<String,Object> map);  /**
     * 查询渠道和账号下的咨询师集合
     * @param map
     * @return
     */
    List<Map<String,Object>> listByAccountCounselorForChannel( Map<String,Object> map);

    /**
     * 渠道审批通过
     * @param map
     * @return
     */
    Result updateChannelApproveStatus(Map<String,Object> map);

    /**
     * 查找未通过认证的咨询师
     * @param map
     * @return
     */
    List<Map<String,Object>> findNotCertificationUser(Map<String, Object> map);
    /**
     * 查询渠道商下面所有的咨询师
     * @param channelId
     * @return
     */
    Result findConsultantForChannel(String  channelId,String startTime,String endTime);
    /**
     * 查询所有的渠道商
     * @return
     */
    List<ChannelManage> findChannelManageAll();

}
