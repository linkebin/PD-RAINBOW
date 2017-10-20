package com.yidusoft.project.cube.questionnaire;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by linkb on 2017/10/17.
 */
@Controller
@RequestMapping(value ={"/cube/"})
public class WebController {
    /**
     * 跳转到订单
     * @return
     */
    @RequestMapping(value ={"/order"})
    public String order(){
            return "project/cube/questionnaire/order";

    }
    /**
     * 跳转到支付
     * @return
     */
    @RequestMapping(value ={"/pay"})
    public String pay(){
            return "project/cube/questionnaire/pay";

    }
    /**
     * 跳转到来访管理
     * @return
     */
    @RequestMapping(value ={"/consultant"})
    public String consultant(){
            return "project/cube/questionnaire/consultant";

    }
    /**
     * 跳转到答题
     * @return
     */
    @RequestMapping(value ={"/questionnaire"})
    public String questionnaire(){
            return "project/cube/questionnaire/questionnaire";

    }
    /**
     * 跳转到来访登记
     * @return
     */
    @RequestMapping(value ={"/visitor"})
    public String visitor(){
            return "project/cube/questionnaire/visitor";
    }
}
