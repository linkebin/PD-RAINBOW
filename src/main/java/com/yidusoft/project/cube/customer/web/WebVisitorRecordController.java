package com.yidusoft.project.cube.customer.web;

import com.yidusoft.project.system.domain.SelectOption;
import com.yidusoft.project.system.service.SelectOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by smy on 2017/10/19.
 */
@Controller
@RequestMapping("/web/visitor/record")
public class WebVisitorRecordController {

    @RequestMapping("/openlist")
    public String openlist(){

        return "project/cube/customer/visitor-manage";
    }

}
