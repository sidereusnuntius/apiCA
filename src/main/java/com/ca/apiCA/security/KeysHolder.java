package com.ca.apiCA.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class KeysHolder {
    protected static RSAPublicKey publicKey;
    protected static RSAPrivateKey privateKey;

    public static void generateKeys() throws NoSuchAlgorithmException {
        if (publicKey == null && privateKey == null) {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            KeyPair pair = generator.generateKeyPair();
            privateKey = (RSAPrivateKey) pair.getPrivate();
            publicKey = (RSAPublicKey) pair.getPublic();
        }
    }
}
