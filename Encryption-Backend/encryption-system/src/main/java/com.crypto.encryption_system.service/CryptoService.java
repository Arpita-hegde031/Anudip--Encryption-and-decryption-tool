package com.crypto.encryption_system.service;

import com.crypto.encryption_system.util.AESUtil;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CryptoService {

    public String encrypt(String text, String method) {
        switch (method.toLowerCase()) {
            case "reverse":
                return new StringBuilder(text).reverse().toString();
            case "caesar":
                int shift = 3;
                StringBuilder caesar = new StringBuilder();
                for (char c : text.toCharArray()) caesar.append((char) (c + shift));
                return caesar.toString();
            case "base64":
                return Base64.getEncoder().encodeToString(text.getBytes());
            case "aes":
                try { return AESUtil.encrypt(text); }
                catch (Exception e) { e.printStackTrace(); return "AES encryption error"; }
            default:
                return "Invalid method";
        }
    }

    public String decrypt(String text, String method) {
        switch (method.toLowerCase()) {
            case "reverse":
                return new StringBuilder(text).reverse().toString();
            case "caesar":
                int shift = 3;
                StringBuilder caesar = new StringBuilder();
                for (char c : text.toCharArray()) caesar.append((char) (c - shift));
                return caesar.toString();
            case "base64":
                try { return new String(Base64.getDecoder().decode(text)); }
                catch (IllegalArgumentException e) { e.printStackTrace(); return "Base64 decryption error"; }
            case "aes":
                try { return AESUtil.decrypt(text); }
                catch (Exception e) { e.printStackTrace(); return "AES decryption error"; }
            default:
                return "Invalid method";
        }
    }
}
