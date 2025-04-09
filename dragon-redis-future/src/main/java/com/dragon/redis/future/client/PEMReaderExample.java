package com.dragon.redis.future.client;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

public class PEMReaderExample {
	public static void main(String[] args) throws Exception {

		PrivateKey privateKey = null;

		// 1. 加载 redis.key 文件
		try (PEMParser pemParser = new PEMParser(new FileReader("C:\\Java\\myredis\\certs\\redis.key"))) {
			Object object = pemParser.readObject();

			// 2. 将 PEM 格式转换为 Java KeyPair
			JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();
			KeyPair keyPair = keyConverter.getKeyPair((org.bouncycastle.openssl.PEMKeyPair) object);

			// 3. 提取私钥
			privateKey = keyPair.getPrivate();
			System.out.println("Private Key: " + privateKey);
		}

		// 加载证书链
		Certificate[] certChain = new Certificate[1]; // 假设只有一个服务器证书
		try (FileInputStream fis = new FileInputStream("C:\\Java\\myredis\\certs\\redis.crt")) {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			certChain[0] = certFactory.generateCertificate(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 创建并初始化 KeyStore
		KeyStore keyStore = KeyStore.getInstance("PKCS12"); // 或使用其他类型，如 "JKS"
		keyStore.load(null, null); // 对于新创建的 KeyStore，传递 null

		// 设置密钥条目（假设私钥没有密码保护，或者密码为空）
		String keyAlias = "redis-key";
		char[] keyPassword = null; // 如果私钥有密码，则提供密码
		keyStore.setKeyEntry(keyAlias, privateKey, keyPassword, certChain);

	}
}
