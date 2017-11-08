package com.yidusoft;

import com.yidusoft.pay.alipay.domain.OrderOnlineBean;
import com.yidusoft.pay.alipay.service.AlipayOrderVerifiedServcie;
import com.yidusoft.project.transaction.domain.OrderOnline;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdRainbowApplicationTests {
	@Resource
	AlipayOrderVerifiedServcie alipayOrderVerifiedServcie;
	private final static Logger logger = LoggerFactory.getLogger(PdRainbowApplicationTests.class);
	@Test
	public void contextLoads() throws InterruptedException {
		OrderOnlineBean orderOnline  = alipayOrderVerifiedServcie.getOrderOnlineByCode("OD000002");
		System.out.println(orderOnline.getProductName()+"@@@@@@@@@@@@@@@@@@");
		System.out.println(orderOnline.getOrderMoney()+"@@@@@@@@@@@@@@@@@@");
		System.out.println(orderOnline.getCreateTime()+"@@@@@@@@@@@@@@@@@@");
		System.out.println(orderOnline.getCreator()+"@@@@@@@@@@@@@@@@@@");
		System.out.println(orderOnline.getPaymentMethod()+"@@@@@@@@@@@@@@@@@@");
	}


}
