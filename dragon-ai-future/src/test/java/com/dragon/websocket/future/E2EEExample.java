package com.dragon.websocket.future;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class E2EEExample {
	public static void main(String[] args) throws Exception {
		// 1. 生成 Alice 和 Bob 的密钥对（ECDH）
		KeyPair aliceKeyPair = generateECDHKeyPair();
		KeyPair bobKeyPair = generateECDHKeyPair();

		// 2. 交换公钥并计算共享密钥
		byte[] aliceSharedSecret = computeSharedSecret(aliceKeyPair.getPrivate(), bobKeyPair.getPublic());
		byte[] bobSharedSecret = computeSharedSecret(bobKeyPair.getPrivate(), aliceKeyPair.getPublic());

		// 3. 生成 AES 对称密钥（取共享密钥前 16 字节）
		SecretKey aliceAESKey = new SecretKeySpec(aliceSharedSecret, 0, 16, "AES");
		SecretKey bobAESKey = new SecretKeySpec(bobSharedSecret, 0, 16, "AES");

		// 4. Alice 加密消息
		String plaintext = "Hello, this is an end-to-end encrypted message!";
		byte[] encryptedMessage = encryptAES(plaintext, aliceAESKey);
		System.out.println("加密后: " + Base64.getEncoder().encodeToString(encryptedMessage));

		// 5. Bob 解密消息
		String decryptedMessage = decryptAES(encryptedMessage, bobAESKey);
		System.out.println("解密后: " + decryptedMessage);
	}

	// 生成 ECDH 密钥对
	public static KeyPair generateECDHKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
		keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
		return keyPairGenerator.generateKeyPair();
	}

	// 计算共享密钥
	public static byte[] computeSharedSecret(PrivateKey privateKey, PublicKey publicKey) throws Exception {
		KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
		keyAgreement.init(privateKey);
		keyAgreement.doPhase(publicKey, true);
		return keyAgreement.generateSecret();
	}

	// AES 加密
	public static byte[] encryptAES(String data, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data.getBytes());
	}

	// AES 解密
	public static String decryptAES(byte[] encryptedData, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedBytes = cipher.doFinal(encryptedData);
		return new String(decryptedBytes);
	}
}