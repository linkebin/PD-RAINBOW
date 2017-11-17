package com.yidusoft.project.transaction.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.AccountInfo;
import com.yidusoft.project.transaction.service.AccountInfoService;
import com.yidusoft.project.transaction.domain.OrderOnline;
import com.yidusoft.project.transaction.domain.ProductSettings;
import com.yidusoft.project.transaction.domain.UserQuestionnaires;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import com.yidusoft.project.transaction.service.ProductSettingsService;
import com.yidusoft.project.transaction.service.UserQuestionnairesService;
import com.yidusoft.utils.Security;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
@RestController
@RequestMapping("/order/online")
@Transactional
public class OrderOnlineController {
    @Resource
    private OrderOnlineService orderOnlineService;

    @Resource
    private UserQuestionnairesService userQuestionnairesService;

    @Resource
    private ProductSettingsService productSettingsService;

    @Resource
    private AccountInfoService accountInfoService;

    /**
     * 添加订单
     *
     * @param onlineJson
     * @return
     */
    @PostMapping("/add")
    public Result add(String onlineJson, String orderCode) {
        OrderOnline orderOnline = JSON.parseObject(onlineJson, OrderOnline.class);
        orderOnline.setId(UUID.randomUUID().toString());
        orderOnline.setOrderCode(orderCode);
        orderOnline.setCreateTime(new Date());
        orderOnline.setCreator(Security.getUser().getUserName());
        orderOnline.setDeleted(0);
        orderOnline.setOrderState(2);
        orderOnline.setUserId(Security.getUserId());
        orderOnlineService.save(orderOnline);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 根据订单状态获取订单信息
     *
     * @param orderState
     * @return
     */
    @PostMapping("/list")
    public Result list(String orderState, int page, int size) {
        PageHelper.startPage(page, size);
        OrderOnline orderOnline = new OrderOnline();
        orderOnline.setUserId(Security.getUser().getId());
        if (orderState != null && orderState != "") {
            Integer state = Integer.parseInt(orderState);
            orderOnline.setOrderState(state);
        }
        List<OrderOnline> list = orderOnlineService.getUserOderById(orderOnline);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 删除订单，逻辑删除，数据还在
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result delete(String id, String orderType) {
        OrderOnline orderOnline = orderOnlineService.findById(id);
        if (orderType.indexOf("cancel") != -1) {
            orderOnline.setOrderState(3);
        } else {
            orderOnline.setDeleted(1);
        }
        orderOnlineService.update(orderOnline);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取订单的状态
     *
     * @param id
     * @return
     */
    @PostMapping("/getState")
    public Result getState(String id) {
        String orderOnline = orderOnlineService.getOrderState(id);
        return ResultGenerator.genSuccessResult(orderOnline);
    }

    /**
     * 付款
     *
     * @param id
     * @return
     */
    @PostMapping("/payment")
    public Result payment(String id, String serialNumber) {
        System.out.println("进入更新@@@@@@@@@@@@@@@@@@@@@@@@@@订单号" + id + "流水号：" + serialNumber);
        OrderOnline orderOnline = orderOnlineService.findById(id);
        orderOnline.setOrderState(1);
        orderOnline.setPaymentTime(new Date());
        orderOnline.setSerialNumber(serialNumber);
        orderOnlineService.update(orderOnline);
        UserQuestionnaires userQuestionnaires = userQuestionnairesService.findBy("userId", orderOnline.getUserId());
        ProductSettings productSettings = productSettingsService.findById(orderOnline.getProductId());
        if (userQuestionnaires != null) {
            if (productSettings.getProductType() == 1) {
                userQuestionnaires.setMember(2);
                userQuestionnaires.setQuestionnairesTotal(userQuestionnaires.getQuestionnairesTotal() + orderOnline.getQuestionnaireTotal());
            } else {
                Date date = new Date();
                if(userQuestionnaires.getEndTime()!=null){
                    date = userQuestionnaires.getEndTime();
                }
                if(userQuestionnaires.getBuyTime()==null){
                    userQuestionnaires.setBuyTime(new Date());
                }
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(date);
                rightNow.add(Calendar.MONTH, productSettings.getTimeLimit());//日期加几个月
                date = rightNow.getTime();
                userQuestionnaires.setMember(1);
                userQuestionnaires.setEndTime(date);
            }
            userQuestionnairesService.update(userQuestionnaires);
        } else {
            UserQuestionnaires userQuestionnaire = new UserQuestionnaires();
            userQuestionnaire.setId(UUID.randomUUID().toString());
            userQuestionnaire.setUserId(orderOnline.getUserId());
            userQuestionnaire.setQuestionnairesCumulativeTotal(0);
            if (productSettings.getProductType() == 1) {
                userQuestionnaires.setMember(2);
                userQuestionnaire.setQuestionnairesTotal(orderOnline.getQuestionnaireTotal());
            }else{
                Date date = new Date();
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(date);
                rightNow.add(Calendar.MONTH, productSettings.getTimeLimit());//日期加几个月
                date = rightNow.getTime();
                userQuestionnaires.setBuyTime(new Date());
                userQuestionnaires.setMember(1);
                userQuestionnaires.setEndTime(date);
            }
            userQuestionnairesService.save(userQuestionnaire);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderCode = formatter.format(new Date());
        orderCode=orderCode+""+(int)Math.random()*100000;
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setId(UUID.randomUUID().toString());
        accountInfo.setSerialNumber(orderCode);
        accountInfo.setAccountDate(new Date());
        accountInfo.setAccountRemarks("购买了"+productSettings.getProductName());
        String buyTotal = orderOnline.getQuestionnaireTotal().toString()+"个月";
        if(productSettings.getProductType()==1){
            buyTotal = orderOnline.getQuestionnaireTotal().toString()+"张";
        }
        accountInfo.setBuyTotal(buyTotal);
        accountInfo.setAccountTotal("--");
        accountInfo.setCostMoney(orderOnline.getOrderMoney()+"元");
        accountInfo.setUserId(orderOnline.getUserId());
        UserQuestionnaires uq = userQuestionnairesService.findBy("userId", orderOnline.getUserId());
        accountInfo.setAccountSurplus(uq.getQuestionnairesTotal());
        accountInfoService.save(accountInfo);
        return ResultGenerator.genSuccessResult();
    }

}
