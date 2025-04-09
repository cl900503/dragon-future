package com.dragon.redis.future.client;

import java.io.FileInputStream;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.SslVerificationMode;
// No SSL certificate verification
//ssc.setSslVerificationMode(SslVerificationMode.NONE);
//KeyManagerFactory keyManagerFactory = keyManagerFactory("C:\\Java\\myredis\\certs\\redis-client.crt", "C:\\Java\\myredis\\certs\\redis-client.key");
//ssc.setSslKeyManagerFactory(keyManagerFactory);
//
//TrustManagerFactory trustManagerFactory = trustManagerFactory("C:\\Java\\myredis\\certs\\ca.crt");
//ssc.setSslTrustManagerFactory(trustManagerFactory);

public class RedissonTest {

	public static void main(String[] args) throws Exception {
		// redisson配置文件
		Config config = new Config();
		config.setCodec(new JsonJacksonCodec());

		// 单机模式
		SingleServerConfig ssc = config.useSingleServer();

		// KeyManagerFactory
		KeyManagerFactory keyManagerFactory = keyManagerFactory("C:\\Java\\myredis\\certs\\redis-client.crt", "C:\\Java\\myredis\\certs\\redis-client.key");
		ssc.setSslKeyManagerFactory(keyManagerFactory);

		// TrustManagerFactory
		TrustManagerFactory trustManagerFactory = trustManagerFactory("C:\\Java\\myredis\\certs\\ca.crt");
		ssc.setSslTrustManagerFactory(trustManagerFactory);

		// 地址
		ssc.setAddress("rediss://192.168.81.98:6379");
		// 密码
		ssc.setPassword("123456");

		RedissonClient redissonClient = Redisson.create(config);

		// 获取 RBucket 对象
		RBucket<String> bucket = redissonClient.getBucket("aa");

		// 设置 Key-Value
		bucket.set("22");

		System.out.println(bucket.get());

	}

	private static KeyManagerFactory keyManagerFactory(String clientCertFile, String clientKeyFile) throws Exception {
		PrivateKey privateKey = null;

		// 1. 加载 redis.key 文件
		try (PEMParser pemParser = new PEMParser(new FileReader("C:\\Java\\myredis\\certs\\redis-client.key"))) {
			Object object = pemParser.readObject();

			// 2. 将 PEM 格式转换为 Java KeyPair
			JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();
			KeyPair keyPair = keyConverter.getKeyPair((org.bouncycastle.openssl.PEMKeyPair) object);

			// 3. 提取私钥
			privateKey = keyPair.getPrivate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 加载证书链
		Certificate[] certChain = new Certificate[1];
		try (FileInputStream fis = new FileInputStream("C:\\Java\\myredis\\certs\\redis-client.crt")) {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			certChain[0] = certFactory.generateCertificate(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 创建并初始化 KeyStore
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(null, null);

		// 设置密钥条目
		String keyAlias = "redis-client";
		char[] keyPassword = null; // 如果私钥有密码，则提供密码
		keyStore.setKeyEntry(keyAlias, privateKey, keyPassword, certChain);

		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keyStore, null);

		return keyManagerFactory;
	}

	private static TrustManagerFactory trustManagerFactory(String caCertFile) throws Exception {

		// 1. 创建一个新的 KeyStore
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(null, null); // 初始化空的 KeyStore

		// 2. 加载 CA 证书
		CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
		Certificate caCertificate;
		try (FileInputStream caCertInputStream = new FileInputStream(caCertFile)) {
			caCertificate = certificateFactory.generateCertificate(caCertInputStream);
		}

		// 3. 将 CA 证书添加到 KeyStore
		String alias = "ca-cert"; // 为证书设置一个别名
		keyStore.setCertificateEntry(alias, caCertificate);

		// 4. 初始化 TrustManagerFactory
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(keyStore);

		return trustManagerFactory;
	}

}
