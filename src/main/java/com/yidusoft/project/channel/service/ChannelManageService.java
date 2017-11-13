package com.yidusoft.project.channel.service;
import com.yidusoft.core.Result;
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

    /**
     * 渠道审批通过
     * @param map
     * @return
     */
    Result updateChannelApproveStatus(Map<String,Object> map);

}
