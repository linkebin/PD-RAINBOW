package com.yidusoft.project.channel.service.impl;

import com.yidusoft.core.Result;
import com.yidusoft.project.channel.dao.ChannelManageMapper;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.core.AbstractService;

import com.yidusoft.project.system.service.SecUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;


/**
 * Created by CodeGenerator on 2017/10/11.
 */
@Service
@Transactional
public class ChannelManageServiceImpl extends AbstractService<ChannelManage> implements ChannelManageService {
    @Resource
    private ChannelManageMapper channelManageMapper;

    @Resource
    private SecUserService secUserService;

    @Override
    public List<ChannelManage> finndChannelByParameterList(ChannelManage channelManage) {
        return channelManageMapper.finndChannelByParameterList(channelManage);
    }

    @Override
    public List<Map<String, Object>> findChannelAccountListByChannelId(Map<String, Object> map) {
        return channelManageMapper.findChannelAccountListByChannelId(map);
    }

    @Override
    public List<Map<String, Object>> findChannelAccountTree(Map<String,Object> map) {
        return channelManageMapper.findChannelAccountTree(map);
    }

    @Override
    public List<Map<String, Object>> findChannelOrAccountCounselorListByParameter(Map<String, Object> map) {
        return channelManageMapper.findChannelOrAccountCounselorListByParameter(map);
    }

    @Override
    public Result updateChannelApproveStatus(Map<String, Object> map) {
        return null;
    }

    /**
     * 查找未通过认证的咨询师
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> findNotCertificationUser(Map<String, Object> map) {
        return channelManageMapper.findNotCertificationUser(map);
    }
}
