package com.example.demo.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {
    public static String encryptPassword(String password)  {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            String hashedPassword = sb.toString();
            return hashedPassword.substring(0, Math.min(hashedPassword.length(), 45));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}