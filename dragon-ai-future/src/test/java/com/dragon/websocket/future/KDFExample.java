package com.dragon.websocket.future;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.google.crypto.tink.subtle.Hkdf;

public class KDFExample {
	public static void main(String[] args) throws Exception {
		// 输入密钥 (IKM)
		byte[] inputKey = "InitialSecretKey".getBytes(StandardCharsets.UTF_8);

		// 可选 salt
		byte[] salt = "RandomSalt".getBytes(StandardCharsets.UTF_8);

		// 额外信息 (info)
		byte[] info = "AdditionalContext".getBytes(StandardCharsets.UTF_8);

		// 目标密钥长度（例如 32 字节）
		int outputKeyLength = 32;

		// 使用 Google Tink 的 HKDF 进行密钥派生
		byte[] derivedKey = Hkdf.computeHkdf("HmacSha256", inputKey, salt, info, outputKeyLength);

		// 输出派生密钥
		System.out.println("Derived Key: " + Arrays.toString(derivedKey));

		char[] chars = Arrays.copyOfRange("1234".toCharArray(), 1, 3);

		for (int i = 0; i < chars.length; i++) {
			System.out.println(chars[i]);
		}

	}
}
