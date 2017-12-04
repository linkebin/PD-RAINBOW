package com.yidusoft.project.channelentrance.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.dao.SecUserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yhdj on 2017/11/29.
 * 客户统计
 */
@RestController
@RequestMapping(value = "/customerStatistics")
public class CustomerStatisticsController {

    @Resource
    private SecUserMapper secUserMapper;
    /**
     * 查找一年中每月客户增长数量
     * @param year
     * @return
     */
    @PostMapping("/findPerMonthCustomerCount")
    public Result findPerMonthCustomerCount(String year){
        try{
            List<Map<String,Object>> list = secUserMapper.findPerMonthCustomerCount();
            return ResultGenerator.genSuccessResult(list);
        }catch (Exception e){
            return ResultGenerator.genFailResult("加载出错了！");
        }
    }

    /**
     *查找每个渠道商邀请的客户
     * @return
     */
    @PostMapping("/findPerChannelCount")
    public Result findPerChannelCount(){
        try {
            List<Map<String,Object>> list = secUserMapper.findPerChannelCount();
            return ResultGenerator.genSuccessResult(list);
        }catch (Exception e){
            return ResultGenerator.genFailResult("加载出错了！");
        }



    }

}
