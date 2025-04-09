package com.dragon.redis.future.client;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class Test {
	public static void main(String[] args) throws Exception {

		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(clusterLettuceConnectionFactory());
		redisTemplate.setDefaultSerializer(RedisSerializer.string());
		redisTemplate.afterPropertiesSet();

		System.out.println(redisTemplate.opsForValue().get("slot:10959:curid"));

	}

	public static RedisConnectionFactory clusterLettuceConnectionFactory() throws Exception {

		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//		configuration.setHostName("172.16.8.158");
//		configuration.setPort(11080);
//		configuration.setPassword("q7ZtCl^5S3");

		configuration.setHostName("192.168.81.98");
		configuration.setPort(6379);
		configuration.setPassword("123456");

		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration);

		lettuceConnectionFactory.afterPropertiesSet();

		return lettuceConnectionFactory;
	}

}
