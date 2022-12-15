package top.devildyw.common.util;

import java.util.Random;

public class PasswordUtil {

    final static Integer DEFAULT_LENGTH = 20;

    public static String getSaltValue() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder(20);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            char ch = chars[random.nextInt(chars.length)];
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String getSaltValue(int n) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder(n);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            char ch = chars[random.nextInt(chars.length)];
            sb.append(ch);
        }
        return sb.toString();
    }
}
