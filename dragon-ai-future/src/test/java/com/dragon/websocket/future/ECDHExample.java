package com.dragon.websocket.future;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.util.Base64;

import javax.crypto.KeyAgreement;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECDHExample {
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		// 生成 Alice 的密钥对
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
		keyGen.initialize(256);
		KeyPair aliceKeyPair = keyGen.generateKeyPair();

		// 生成 Bob 的密钥对
		KeyPair bobKeyPair = keyGen.generateKeyPair();

		// Alice 计算共享密钥
		KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("ECDH");
		aliceKeyAgree.init(aliceKeyPair.getPrivate());
		aliceKeyAgree.doPhase(bobKeyPair.getPublic(), true);
		byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();

		// Bob 计算共享密钥
		KeyAgreement bobKeyAgree = KeyAgreement.getInstance("ECDH");
		bobKeyAgree.init(bobKeyPair.getPrivate());
		bobKeyAgree.doPhase(aliceKeyPair.getPublic(), true);
		byte[] bobSharedSecret = bobKeyAgree.generateSecret();

		// 输出共享密钥（两者相同）
		System.out.println("Alice Shared Key: " + Base64.getEncoder().encodeToString(aliceSharedSecret));
		System.out.println("Bob Shared Key: " + Base64.getEncoder().encodeToString(bobSharedSecret));
	}
}
