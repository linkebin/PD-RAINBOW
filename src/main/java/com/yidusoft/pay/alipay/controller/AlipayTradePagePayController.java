package com.yidusoft.pay.alipay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yidusoft.pay.alipay.domain.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc 支付宝支付接口
 * @create 2017-10-19 11:02
 **/
@Controller
@RequestMapping("/alipaytradepagepay")
public class AlipayTradePagePayController {



    @RequestMapping("/paymentByAlipay")
    public void paymentByAlipay(HttpServletRequest request, HttpServletResponse response, String formDatas) throws IOException {

        // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        String NOTIFY_URL = "http://"+request.getServerName()+":"+request.getServerPort()+"/alipay/notify/notifyUrl";

        // 页面跳转同步通知页面路径
        //网页重定向通知，由客户端浏览器触发的通知，可本地调试，若客户去网银支付，也会受银行接口影响，由于各种影响因素特别多，所以该种类型的通知支付宝不保证其到达率。
        //买家付款成功后,会跳到 return_url所在的页面,这个页面可以展示给客户看,这个页面只有付款成功才会跳转,并且只跳转一次
        String RETURN_URL = "http://"+request.getServerName()+":"+request.getServerPort()+"/alipay/notify/returnUrl";

        //接收订单请求的参数
        JSONObject jsonObject = JSON.parseObject(formDatas);
        //订单编号
        String outTradeNo = jsonObject.getString("orderCode");
        //支付金额
        String totalAmount = jsonObject.getString("total_amount");
        //商品标题
        String subject = jsonObject.getString("subject");
        //订单描述
        String body = jsonObject.getString("body");
        //请求数据对象添加支付宝产品编号，即时到账
        String productCode = "FAST_INSTANT_TRADE_PAY";
        String bizContent = "{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"" + productCode + "\"}";
        //实例化支付端对象
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", AlipayConfig.APP_ID, AlipayConfig.MERCHANT_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        //实例化网站支付接口请求对象
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        //设置同步通知页面
        alipayTradePagePayRequest.setReturnUrl(RETURN_URL);
        //设置异步通知页面
        alipayTradePagePayRequest.setNotifyUrl(NOTIFY_URL);
        //请求数据
        alipayTradePagePayRequest.setBizContent(bizContent);
        String formString = "";
        try {
            //生成前台页面请求需要的完整form表单的html（包含自动提交脚本）
            formString = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //表单写入响应
        String str = "form";
        //ajax请求，将表单内容append到节点，form开启新的页面展示，拼接target
        //form的submit提交方式，不需拼接 target
//        String newStr = formString.substring(0, formString.indexOf(str) + 4) + " target='_blank'" + formString.substring(formString.indexOf(str) + 4);
        //响应格式html
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        //响应数据写入
        response.getWriter().write(formString);
        //清空流缓冲区，完成写入操作
        response.getWriter().flush();
        //流关闭
        response.getWriter().close();

    }
}
