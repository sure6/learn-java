package com.leesure;

import java.security.MessageDigest;
import java.util.Base64;

public class DigestUtil {

    public static String sha256(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(input.getBytes("UTF-8"));
        // 返回 Hex 或 Base64
        return bytesToHex(digest);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String text = "Hello world";
        System.out.println("SHA-256: " + sha256(text));
    }
}