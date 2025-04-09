package com.dragon.redis.future.client;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SslOptions;

public class Test1 {
	public static void main(String[] args) throws Exception {

		SslOptions sslOptions = SslOptions.builder().jdkSslProvider().build();

		ClientOptions clientOptions = ClientOptions.builder().sslOptions(sslOptions).build();

		LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder().useSsl().disablePeerVerification().and().clientOptions(clientOptions).build();

		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName("192.168.81.98");
		configuration.setPort(6379);
		configuration.setPassword("123456");

		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration, lettuceClientConfiguration);

		lettuceConnectionFactory.afterPropertiesSet();

		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(lettuceConnectionFactory);
		redisTemplate.setDefaultSerializer(RedisSerializer.string());
		redisTemplate.afterPropertiesSet();

		redisTemplate.opsForValue().set("b", "22");

		System.out.println(redisTemplate.opsForValue().get("b"));

	}

}
