package com.example.finalprojectapi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

@Component
public class PasswordUtils {
    private static final String CIPHER_INSTANCE_NAME = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY_ALGORITHM = "AES";
    public static String ENCRYPTION_KEY;

    @Value("${password.encryption.key}")
    public void setEncryptionKey(String encryptionKey) {
        ENCRYPTION_KEY = encryptionKey;
    }


    private static Cipher prepareAndInitCipher(int encryptionMode) throws Exception {
        byte[] secretKey = getKey(ENCRYPTION_KEY);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, SECRET_KEY_ALGORITHM);

        String iv = new String(Hex.encode(secretKey)).substring(0, 16);
        IvParameterSpec algorithmParameters = new IvParameterSpec(iv.getBytes());

        Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE_NAME);
        cipher.init(encryptionMode, secretKeySpec, algorithmParameters);

        return cipher;
    }

    private static byte[] getKey(String originalKey) throws Exception
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(originalKey.getBytes());
    }

    public static String encrypt(String plainText){
        try {

            byte[] dataBytes = plainText.getBytes();
            int plaintextLength = dataBytes.length;
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            Cipher cipher = prepareAndInitCipher(Cipher.ENCRYPT_MODE);

            byte[] encrypted = cipher.doFinal(plaintext);
            return new String(Base64.getEncoder().encode(encrypted));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encryptedText) throws Exception {
        byte[] encrypted = Base64.getDecoder().decode(encryptedText);

        Cipher cipher = prepareAndInitCipher(Cipher.DECRYPT_MODE);

        byte[] original = cipher.doFinal(encrypted);
        return new String(original);
    }

    public static void main(String[] args) throws Exception {
        System.err.println(PasswordUtils.encrypt("test"));
        System.err.println(PasswordUtils.decrypt("tv1qsfsQK3WTVKY8ZF3Gzg=="));
    }
}
