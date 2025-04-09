package com.dragon.websocket.future;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.util.Base64;

import javax.crypto.KeyAgreement;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class X3DHExample {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // 1️⃣ 生成 Alice 的密钥对 (IK_A, EK_A)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDH", "BC");
        keyGen.initialize(256);
        KeyPair aliceIdentityKeyPair = keyGen.generateKeyPair(); // IK_A
        KeyPair aliceEphemeralKeyPair = keyGen.generateKeyPair(); // EK_A

        // 2️⃣ 生成 Bob 的密钥对 (IK_B, SPK_B, OPK_B)
        KeyPair bobIdentityKeyPair = keyGen.generateKeyPair(); // IK_B
        KeyPair bobSignedPreKeyPair = keyGen.generateKeyPair(); // SPK_B
        KeyPair bobOneTimePreKeyPair = keyGen.generateKeyPair(); // OPK_B

        // 3️⃣ Alice 计算共享密钥 SK
        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("ECDH", "BC");
        aliceKeyAgree.init(aliceEphemeralKeyPair.getPrivate());
        aliceKeyAgree.doPhase(bobIdentityKeyPair.getPublic(), true);
        byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();

        // 4️⃣ Bob 计算共享密钥 SK
        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("ECDH", "BC");
        bobKeyAgree.init(bobIdentityKeyPair.getPrivate());
        bobKeyAgree.doPhase(aliceEphemeralKeyPair.getPublic(), true);
        byte[] bobSharedSecret = bobKeyAgree.generateSecret();

        // 5️⃣ 输出共享密钥（Alice 和 Bob 的结果应相同）
        System.out.println("Alice Shared Key: " + Base64.getEncoder().encodeToString(aliceSharedSecret));
        System.out.println("Bob Shared Key: " + Base64.getEncoder().encodeToString(bobSharedSecret));
    }
}
