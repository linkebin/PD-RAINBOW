package com.yidusoft.project.channelentrance.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yhdj on 2017/11/29.
 * 客户统计
 */
@Controller
@RequestMapping(value = "/web/customerStatistics")
public class WebCustomerStatisticsController {

    /**
     * 跳转到客户统计页面
     * @return
     */
    @RequestMapping("/CustomerStatistics")
    public String CustomerStatistics(){
        return "project/channelentrance/CustomerStatistics";
    }
}
