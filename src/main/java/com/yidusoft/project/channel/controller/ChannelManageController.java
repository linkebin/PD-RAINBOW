package com.yidusoft.project.channel.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.activitis.service.ChannelActivityService;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.channel.service.ChannelRuleService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.DateUtils;
import com.yidusoft.utils.PasswordHelper;
import com.yidusoft.utils.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/channel/manage")
public class ChannelManageController {
    @Resource
    private ChannelManageService channelManageService;

    @Resource
    private ChannelRuleService channelRuleService;

    @Autowired
    private ChannelActivityService channelActivityService;

    @PostMapping("/add")
    public Result add(String json) {
        ChannelManage channelManage = JSON.parseObject(json,ChannelManage.class);
        channelManage.setId(UUID.randomUUID().toString());
        channelManage.setChannelCode(CodeHelper.getCode("QD"));
        channelManage.setCreator(Security.getUser().getUserName());
        channelManage.setDeleted(0);

        SecUser secUser = new SecUser();
        secUser.setId(UUID.randomUUID().toString());
        secUser.setAccountType(2);
        secUser.setAccount(channelManage.getLinkmanTell());
        secUser.setUserName(channelManage.getLinkman());
        secUser.setSex(0);
        secUser.setStatus(0);
        secUser.setHeadImg("/upload/headImg/default-pic.png");
        secUser.setUserPass(PasswordHelper.strToMd5(secUser.getAccount()));
        secUser.setMobile(secUser.getAccount());
        secUser.setChannelId(channelManage.getId());

        String inviterCode = CodeHelper.randomCode(8);
        SecUser isUser = null;
        if (inviterCode!=null){
            isUser = secUserService.findSecUserByInviterCode(inviterCode);
        }
        if (isUser != null){
            add(json);
        }else {
            secUser.setInviterCode(inviterCode);
            try {
                Result result = secUserService.addUser(JSON.toJSONString(secUser));
                if (result.getCode() !=200){
                    return result;
                }
                channelManageService.save(channelManage);
            }catch (Exception e){
                e.printStackTrace();
                return ResultGenerator.genFailResult("添加失败");
            }
        }
        return ResultGenerator.genSuccessResult("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        channelManageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String  json) {
        ChannelManage channelManage = JSON.parseObject(json,ChannelManage.class);
        channelManageService.update(channelManage);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/deleteBacth")
    public Result deleteBacth(String ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            ChannelManage channelManage = channelManageService.findById(str);
            if (channelManage!=null){
                channelManage.setDeleted(1);
                channelManageService.update(channelManage);
            }
        }
        return ResultGenerator.genSuccessResult("删除成功！");
    }

    @PostMapping("/detail")
    public Result detail(String id) {
        ChannelManage channelManage = channelManageService.findById(id);
        return ResultGenerator.genSuccessResult(channelManage);
    }

    @PostMapping("/listByparameter")
    public Result list(int page,  int pagesize,String json) {

        ChannelManage channelManage = JSON.parseObject(json,ChannelManage.class);
        PageHelper.startPage(page, pagesize);
        List<ChannelManage> list = channelManageService.finndChannelByParameterList(channelManage);
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/channelTree")
    public Result channelTree() {
        ChannelManage channelManage = new ChannelManage();
        channelManage.setStatus(2);
        channelManage.setJoinStartTime("");
        channelManage.setJoinEndTime("");
        List<ChannelManage> list = channelManageService.finndChannelByParameterList(channelManage);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/listByparameterSelect")
    public Result listByparameterSelect(int page,  int pagesize,String json) {
        Map map = JSON.parseObject(json,Map.class);

        List<String>  ids = new ArrayList<String>();
        List<Map<String,Object>> maps = channelRuleService.findChannelRuleTimeClashList(map);
        for (Map<String,Object> m : maps){
           boolean flag = DateUtils.isOverlap(map.get("start").toString(),map.get("end").toString(),
                    m.get("start_time").toString(), m.get("end_time").toString());
            System.out.println(flag +":"+m.get("start_time").toString()+"---"+m.get("end_time").toString());
           if (flag){
               ids.add(m.get("channel_id").toString());
           }
        }
        PageHelper.startPage(page, pagesize);
        List<ChannelManage> list = channelManageService.finndChannelNotInIdAndParameter(ids,map);

        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/listByaccounts")
    public Result listByaccounts(Integer page,  Integer limit,String json) {

        Map<String,Object> map = JSON.parseObject(json,Map.class);

        PageHelper.startPage(page, limit);
        List<Map<String,Object>> list = channelManageService.findChannelAccountListByChannelId(map);
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }
    @Autowired
    private SecUserService secUserService;

    @PostMapping("/channelAccountAdd")
    public Result channelAccountAdd(String params) {
        SecUser secUser = JSON.parseObject(params,SecUser.class);
        secUser.setCreator(Security.getUser().getUserName());
        secUser.setHeadImg("/upload/headImg/default-pic.png");
        String inviterCode = CodeHelper.randomCode(8);
        SecUser isUser = null;
        if (inviterCode!=null){
            isUser = secUserService.findSecUserByInviterCode(inviterCode);
        }
        if (isUser != null){
            channelAccountAdd(params);
        }else {
            secUser.setInviterCode(inviterCode);
            return  secUserService.addUser(JSON.toJSONString(secUser));
        }
        return ResultGenerator.genFailResult("添加失败");
    }

    @PostMapping("/accountInfo")
    public Result accountInfo(String id) {
        SecUser secUser = secUserService.findById(id);
        return ResultGenerator.genSuccessResult(secUser);
    }

    @PostMapping("/updateaccountInfo")
    public Result updateaccountInfo(String json) {
        SecUser secUser = JSON.parseObject(json,SecUser.class);
        try {
            secUserService.update(secUser);
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("编辑失败！");
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/accounttree")
    public Result accounttree(String json) {

        Map<String,Object> map = JSON.parseObject(json,Map.class);

        return ResultGenerator.genSuccessResult(channelManageService.findChannelAccountTree(map));
    }

    @PostMapping("/listByAccountCounselor")
    public Result listByAC(Integer page,  Integer limit,String json) {

        Map<String,Object> map = JSON.parseObject(json,Map.class);
        List<Map<String,Object>> channels = channelManageService.findChannelListAndTypeAndParameter(map);
        List<String> ids = new ArrayList<String>();
        for (Map<String,Object> m3 : channels){
            ids.add(m3.get("id_").toString());
        }
        if (ids.size() == 0){
            ids.add("9x991000");
        }
        map.put("type",2);
        PageHelper.startPage(page, limit);
        List<Map<String,Object>> list = channelManageService.findChannelOrAccountCounselorListByParameter(ids,map);
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }
        //    渠道商查詢咨詢師列表
    @PostMapping("/listByAccountCounselorForChannel")
    public Result listByAccountCounselorForChannel(Integer page,  Integer limit,String json) {

        Map<String,Object> map = JSON.parseObject(json,Map.class);
        PageHelper.startPage(page, limit);
        List<Map<String,Object>> list = channelManageService.listByAccountCounselorForChannel(map);
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(list).setCount(pageInfo.getTotal()).setCode(0);
    }
    /**
     * 查找未通过认证的咨询师
     * @param params
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("/findNotCertificationUser")
    public Result findNotCertificationUser(String params,Integer page,Integer limit){
        Map<String,Object> map = JSON.parseObject(params,Map.class);
        PageHelper.startPage(page,limit);
        try {
            List<Map<String,Object>> list = channelManageService.findNotCertificationUser(map);
            return ResultGenerator.genSuccessResult(list);
        }catch (Exception e){
            return ResultGenerator.genFailResult("出错了");
        }
    }

    /**
     * 查询所有的渠道商
     * @return
     */
    @PostMapping("/findChannelManageAll")
    public Result findChannelManageAll(){
      List<ChannelManage> channelManageAll = channelManageService.findChannelManageAll();
      return ResultGenerator.genSuccessResult(channelManageAll);
    }
    /**
     * 查询渠道商下面所有的咨询师
     * @return
     */
    @PostMapping("/findConsultantForChannel")
    public Result findConsultantForChannel(String channelId,String endTime,String startTime){

        return channelManageService.findConsultantForChannel(channelId,startTime,endTime);
    }

}
