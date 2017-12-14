package com.yidusoft.project.channel.service.impl;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.dao.ChannelManageMapper;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.core.AbstractService;

import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.TimeStampDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    public List<ChannelManage> finndChannelNotInIdAndParameter(List<String> ids, Map<String, Object> map) {
        return channelManageMapper.finndChannelNotInIdAndParameter(ids,map);
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
    /**
     * 查询渠道商下面所有的咨询师
     * @param channelId
     * @return
     */
    @Override
    public Result findConsultantForChannel(String  channelId,String startTime,String endTime ) {
        Map<String,Object> maps=new HashMap<>();
        maps.put("channelId",channelId);
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            if(!"".equals(startTime)){
                Date sTime=sdf.parse(startTime);
                Date eTime=sdf.parse(endTime);
            maps.put("startTime", sTime);
            maps.put("endTime",eTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //市区的人数
        List<Map<String,Object>>   findChannelForCity=channelManageMapper.findCustomerForCity(maps);
       //查询渠道商下面所有的咨询师
        List<Map<String,Object>> consultantForChannel=channelManageMapper.findConsultantForChannel(maps);

        List<Map<String,Object>> channelForTime=channelManageMapper.findChannelForTime(maps);

        Map<String,Object> map=new HashMap<>();
        map.put("cityNums",findChannelForCity);
        map.put("consultantAll",consultantForChannel);
        map.put("channelForTimeAll",channelForTime);

        return ResultGenerator.genSuccessResult(map);
    }

    /**
     * 查询所有的渠道商
     * @return
     */
    @Override
    public List<ChannelManage> findChannelManageAll() {
        return channelManageMapper.findChannelManageAll();
    }



}
