package com.yidusoft.project.cube.customer;

import com.yidusoft.core.Result;
import com.yidusoft.project.business.domain.VisitorRegister;
import com.yidusoft.project.business.service.VisitorRegisterService;
import com.yidusoft.project.system.domain.SelectOption;
import com.yidusoft.project.system.service.SelectOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by smy on 2017/10/19.
 */
@RestController
@RequestMapping("/cube/customer")
public class CubeCustomerController {
    @Autowired
    private SelectOptionService selectOptionService;

    @Autowired
    private VisitorRegisterService visitorRegisterService;


    //为来访者 添加填写问卷
    @PostMapping("/getCheckQuestionnaireForVisitor")
    public Result getCheckQuestionnaireForVisitor(){
        return visitorRegisterService.getCheckQuestionnaireForVisitor( );
    }
}
