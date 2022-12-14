package com.hsbc.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtils {


    private static final String encodeRules = "hsbc";
    static Base64.Encoder encoder = Base64.getEncoder();
    static Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 加密
     */
    public static String AESEncode(String content) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            keygen.init(128, random);
            SecretKey original_key = keygen.generateKey();
            byte[] raw = original_key.getEncoded();
            SecretKey key = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] byte_encode = content.getBytes("utf-8");
            byte[] byte_AES = cipher.doFinal(byte_encode);
            String AES_encode = new String(encoder.encode(byte_AES));
            System.out.println("AED_encode :" + AES_encode);
            return AES_encode;
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | IllegalBlockSizeException
                | BadPaddingException
                | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解密
     */
    public static String AESDecode(String content) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            keygen.init(128, random);
            SecretKey original_key = keygen.generateKey();
            byte[] raw = original_key.getEncoded();
            SecretKey key = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] byte_content = decoder.decode(content);
            /*
             * 解密
             */
            byte[] byte_decode = cipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode, "utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | IOException
                | IllegalBlockSizeException
                | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String[] keys = {
                "", "root"
        };
        System.out.println("key | AESEncode | AESDecode");
        for (String key : keys) {
            System.out.print("key:" + key + " | ");
            String encryptString = AESEncode(key);
            System.out.print(encryptString + " | ");
            String decryptString = AESDecode(encryptString);
            System.out.println(decryptString);
        }
    }
}