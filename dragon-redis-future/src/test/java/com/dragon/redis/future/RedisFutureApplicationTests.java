package com.dragon.redis.future;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisFutureApplicationTests {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Test
	void Test() {
		redisTemplate.opsForValue().set("aa", "100");
		String value = redisTemplate.opsForValue().get("aa");
		System.out.println(value);
	}

}
