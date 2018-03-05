package com.yidusoft.project.channel.service.impl;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.AbstractService;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.activitis.service.ChannelActivityService;
import com.yidusoft.project.channel.dao.ChannelManageMapper;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


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

    @Resource
    private ChannelActivityService channelActivityService;

    @Override
    public Result signChannel(SecUser secUser, ChannelManage channelManage) throws Exception{
        try {
            Result result = secUserService.addUser(JSON.toJSONString(secUser));
            if (result.getCode() !=200){
                return result;
            }
            this.save(channelManage);
            String uri = "/web/channel/manage/acdetail?id="+channelManage.getId();
            channelActivityService.startProcess(channelManage.getId(),channelManage.getChannelName()+"  申请入驻平台",uri);
            return ResultGenerator.genSuccessResult().setMessage("登记成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("登记失败");
        }
    }

    @Override
    public List<Map<String, Object>> findChannelListAndTypeAndParameter(Map<String, Object> map) {
        return channelManageMapper.findChannelListAndTypeAndParameter(map);
    }

    @Override
    public List<Map<String, Object>> findAreaChannelTree() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("type","ProvincesGrouped");

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String,Object> leveL0 = new HashMap<String,Object>();
        leveL0.put("id", 0);
        leveL0.put("name","全部");
        leveL0.put("pid","xx");
        leveL0.put("level",0);
        data.add(leveL0);

       List<Map<String,Object>> provinces = channelManageMapper.findChannelListAndTypeAndParameter(map);
       int count =1;
       for(Map<String,Object> m :provinces){
           //第1级省
           Map<String,Object> leveL1 = new HashMap<String,Object>();
           leveL1.put("id", "leveL1"+count);
           leveL1.put("name",m.get("province"));
           leveL1.put("pid",0);
           leveL1.put("level",1);
           data.add(leveL1);

           map.put("type","cityList");
           map.put("province",m.get("province").toString());
           List<Map<String,Object>> citys = channelManageMapper.findChannelListAndTypeAndParameter(map);
            int count2 = 1;
           for(Map<String,Object> m2 :citys){
               //第2级市
               Map<String,Object> leveL2 = new HashMap<String,Object>();
               leveL2.put("id","leveL1"+count+count2);
               leveL2.put("name",m2.get("city"));
               leveL2.put("pid",leveL1.get("id"));
               leveL2.put("level",2);

               //第3级渠道
               Map<String,Object> leveL3 = new HashMap<String,Object>();
               leveL3.put("id",m2.get("id_"));
               leveL3.put("name",m2.get("channel_name"));
               leveL3.put("pid",leveL2.get("id"));
               leveL3.put("level",3);

               data.add(leveL2);
               data.add(leveL3);
               count2++;
           }
           count++;
       }

        return data;
    }

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
    public List<Map<String, Object>> findChannelOrAccountCounselorListByParameter(List<String> ids,
                                                                                  Map<String,Object> map) {
        return channelManageMapper.findChannelOrAccountCounselorListByParameter(ids,map);
    }

    @Override
    public List<Map<String, Object>> listByAccountCounselorForChannel(Map<String, Object> map) {
        return channelManageMapper.listByAccountCounselorForChannel(map);
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
