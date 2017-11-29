package com.yidusoft.project.transaction.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by L on 2017/10/12.
 */
@Controller
@RequestMapping("web/product")
public class WebProductSettingsController {
    @RequestMapping("/setting")
    public String setting(){
        return "project/transaction/product-setting/productSetting";
    }

    @RequestMapping("/linkList")
    public String linkList(){
        return "project/transaction/product/productList";
    }

    @RequestMapping("/trajectory")
    public String trajectory(){ return "project/transaction/product/productTrajectory"; }

    @RequestMapping("/sales")
    public String sales(){ return "project/transaction/product/productSales"; }
}
