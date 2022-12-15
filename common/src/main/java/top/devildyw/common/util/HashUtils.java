/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package top.devildyw.common.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * hash工具类 主要用于密码加密
 * @author Devildyw
 * @version 1.0
 */
public class HashUtils {

    private final static Integer DEFAULT_HASH_ITERATIONS = 1024;

    private final static String DEFAULT_ALGORITHM_NAME = "SHA-256";

    public static MessageDigest getDigest(String algorithmName) {
        try {
            return MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException var4) {
            String msg = "No native '" + algorithmName + "' MessageDigest instance available on the current JVM.";

        }
        return null;
    }
    public static String hashHex(String algorithmName, String source, String salt, int hashIterations) {
        if (salt == null) {
            return hashHex(algorithmName, source.getBytes(StandardCharsets.UTF_8), null, hashIterations);
        } else {
            return hashHex(algorithmName, source.getBytes(StandardCharsets.UTF_8), salt.getBytes(StandardCharsets.UTF_8), hashIterations);
        }

    }

    public static String hashHex(String source, String salt){
        if (salt == null) {
            return hashHex(DEFAULT_ALGORITHM_NAME, source.getBytes(StandardCharsets.UTF_8), null, DEFAULT_HASH_ITERATIONS);
        } else {
            return hashHex(DEFAULT_ALGORITHM_NAME, source.getBytes(StandardCharsets.UTF_8), salt.getBytes(StandardCharsets.UTF_8), DEFAULT_HASH_ITERATIONS);
        }
    }


    public static String hashHex(String algorithmName, byte[] bytes, byte[] salt, int hashIterations) {
        byte[] hash = hash(bytes, algorithmName, salt, hashIterations);
        return Hex.encodeHexString(hash);
    }

    public static byte[] hash(byte[] bytes, String algorithmName, byte[] salt, int hashIterations) {
        MessageDigest digest = getDigest(algorithmName);
        if (salt != null) {
            digest.reset();
            digest.update(salt);
        }

        byte[] hashed = digest.digest(bytes);
        int iterations = hashIterations - 1;

        for(int i = 0; i < iterations; ++i) {
            digest.reset();
            hashed = digest.digest(hashed);
        }

        return hashed;
    }


    public static void main(String[] args) {
        System.out.println("9bf9a65d9fd05e026939ab6924908ba064de0517f0546f4ef9394d557b745721".length());
    }

}
