package com.yidusoft.project.cube.customer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by smy on 2017/10/19.
 */
@Controller
@RequestMapping("/web/customer")
public class WebCustomerController {

    @RequestMapping("/customerList")
    public String customerList(){

        return "project/cube/customer/customer-list";
    }

    @RequestMapping("/visitorRegister")
    public String visitorRegister(){

        return "project/cube/customer/visitor-register";
    }

    @RequestMapping("/111")
    public String sw(){

        return "test";
    }
}
