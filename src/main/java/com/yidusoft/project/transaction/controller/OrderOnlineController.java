package com.yidusoft.project.transaction.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.OrderOnline;
import com.yidusoft.project.transaction.service.OrderOnlineService;
import com.yidusoft.project.transaction.service.QuestionnairePromotionsService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
@RestController
@RequestMapping("/order/online")
public class OrderOnlineController {
    @Resource
    private OrderOnlineService orderOnlineService;

    @Resource
    private QuestionnairePromotionsService questionnairePromotionsService;

    /**
     * 添加订单
     *
     * @param onlineJson
     * @return
     */
    @Transactional
    @PostMapping("/add")
    public Result add(String onlineJson, String promotionId) {
        OrderOnline orderOnline = JSON.parseObject(onlineJson, OrderOnline.class);
        orderOnline.setId(UUID.randomUUID().toString());
        orderOnline.setOrderCode(CodeHelper.getCode("OD"));
        orderOnline.setCreateTime(new Date());
        orderOnline.setCreator(Security.getUser().getUserName());
        orderOnline.setDeleted(0);
        orderOnline.setOrderState(1);
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
    public Result list(String orderState) {
        OrderOnline orderOnline = new OrderOnline();
        orderOnline.setUserId(Security.getUser().getId());
        if (orderState != null && orderState != "") {
            Integer state = Integer.parseInt(orderState);
            orderOnline.setOrderState(state);
        }
        List<OrderOnline> list = orderOnlineService.getUserOderById(orderOnline);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 删除订单，逻辑删除，数据还在
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result delete(String id) {
        OrderOnline orderOnline = orderOnlineService.findById(id);
        orderOnline.setDeleted(1);
        orderOnlineService.update(orderOnline);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(OrderOnline orderOnline) {
        orderOnlineService.update(orderOnline);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        OrderOnline orderOnline = orderOnlineService.findById(id);
        return ResultGenerator.genSuccessResult(orderOnline);
    }
}
