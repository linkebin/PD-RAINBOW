package com.yidusoft.pay.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.yidusoft.pay.alipay.domain.AlipayConfig;
import com.yidusoft.pay.alipay.domain.OrderOnlineBean;
import com.yidusoft.pay.alipay.service.AlipayOrderVerifiedServcie;
import com.yidusoft.project.transaction.controller.OrderOnlineController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝通知处理层
 */
@Controller
@RequestMapping("/alipay/notify")
public class AlipayNotifyController {

    @Resource
    private AlipayOrderVerifiedServcie alipayOrderVerifiedServcie;

    @Resource
    OrderOnlineController orderOnlineController;
    /**
     * 接口异步通知
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/notifyUrl")
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> params = parseRequestParamToMap(request);
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE); //调用SDK验证签名
            /* 实际验证过程建议商户务必添加以下校验：
            1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
            2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
            3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
            4、验证app_id是否为该商户本身。
            */
            if (signVerified) {//验证成功
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
                //交易状态
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
                //商户支付宝用户号
                String seller_id = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"), "UTF-8");
                //商户应用id
                String app_id = new String(request.getParameter("app_id").getBytes("ISO-8859-1"), "UTF-8");

                OrderOnlineBean orderOnline = alipayOrderVerifiedServcie.getOrderOnlineByCode(out_trade_no);
                if (orderOnline != null) {//是否能通过订单号查找到该订单
                    BigDecimal df = new BigDecimal(total_amount);
                    if (0==df.compareTo(orderOnline.getOrderMoney())) {//判断金额是否正确
                        //判断是否为商户用户号，商户可能有多个用户号，此处后期稍有变动
                        if (seller_id.equals("2088102171354664")) {
                            //检验商户应用ID
                            if (AlipayConfig.APP_ID.equals(app_id)) {
                                //触发条件是商户不支持退款，买家付款成功；或者支持退款，超过退款期限
                                if (trade_status.equals("TRADE_FINISHED")) {
                                    System.out.println("@@@@@@@@@@订单状态FINISHED@@@@@@@@@@@@@@@@@@@@@@@");
                                    //付款成功调用订单更新接口
                                    orderOnlineController.payment(orderOnline.getId(),trade_no);
                                    response.getWriter().write("success");
                                } else if (trade_status.equals("TRADE_SUCCESS")) {//支付成功的触发条件是商户支持退款
                                    //付款成功调用订单更新接口
                                    System.out.println("@@@@@@@@@@订单状态SUCCESS@@@@@@@@@@@@@@@@@@@@@@@");
                                    orderOnlineController.payment(orderOnline.getId(),trade_no);
                                    response.getWriter().write("success");
                                }

                                System.out.println("&&&&&&&&&&&异步通知部分结束&&&&&&&&&&&&&&&&&&");
                                response.getWriter().write("success");
                            }
                        }
                    }
                } else {
                    response.getWriter().write("failure");
                }

            } else {//验证失败
                response.getWriter().write("failure");

                //调试用，写文本函数记录程序运行情况是否正常
                //String sWord = AlipaySignature.getSignCheckContentV1(params);
                //AlipayConfig.logResult(sWord);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接口同步通知
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/returnUrl")
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //封装参数集合
        Map<String, String> params = parseRequestParamToMap(request);
        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
            if (signVerified) {//验签成功
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

//                response.getWriter().write("订单号:" + out_trade_no + "<br/>支付宝交易号:" + trade_no + "<br/>付款金额:" + total_amount);
                //支付完成，重定向首页
                response.sendRedirect("/index");
            } else {
                response.getWriter().write("同步通知验签失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将请求的参数封装为Map
     *
     * @param request
     * @return
     */
    @RequestMapping("/parseRequestParamToMap")
    public Map<String, String> parseRequestParamToMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        //获取请求参数Map集合
        Map<String, String[]> requestParams = request.getParameterMap();
        //迭代Map集合
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            //获取key
            String name = (String) iter.next();
            //获取value数组
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            //使用逗号拼接数组的值
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }
}
