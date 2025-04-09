package com.dragon.redis.future.client;

import java.io.File;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SslOptions;

public class SslTest {
	public static void main(String[] args) throws Exception {

		SslOptions sslOptions = SslOptions.builder().jdkSslProvider().sslContext(sslContextBuilder -> {
			sslContextBuilder.trustManager(new File("C:\\Java\\myredis\\certs\\ca.crt"));
			sslContextBuilder.keyManager(new File("C:\\Java\\myredis\\certs\\redis-client.crt"), new File("C:\\Java\\myredis\\certs\\redis-client.key"));
		}).build();

		ClientOptions clientOptions = ClientOptions.builder().sslOptions(sslOptions).build();

		LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder().useSsl().and().clientOptions(clientOptions).build();

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
