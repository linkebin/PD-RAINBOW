package com.yidusoft;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdFainbowApplicationTests {
	private final static Logger logger = LoggerFactory.getLogger(PdFainbowApplicationTests.class);

	@Autowired
	JedisPool jedisPool;
	@Autowired
	StringRedisTemplate template;
	@Autowired
	CountDownLatch latch;
	@Test
	public void contextLoads() throws InterruptedException {

//		String uuid = UUID.randomUUID().toString();
//		logger.info("jedisPool uuid : " + uuid);
//		try (Jedis jedis = jedisPool.getResource()) {
//			jedis.set("2", "2");
//		}


		logger.info("Sending message...");
		template.convertAndSend("chat", "Hello from Redis!1");
		template.convertAndSend("chat", "Hello from Redis!2");
		template.convertAndSend("chat", "Hello from Redis!3");
		template.convertAndSend("chat", "Hello from Redis!4");
		template.convertAndSend("chat", "Hello from Redis!5");
		latch.await();


	}

}
