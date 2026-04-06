package com.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    // 简单的MD5加密方法
    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return password; // 如果加密失败，返回原密码(不推荐)
        }
    }
}
