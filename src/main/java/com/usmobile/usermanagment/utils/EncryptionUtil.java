package com.usmobile.usermanagment.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionUtil {

    public static String encode(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);

    }

    private static byte[] decode(String encodedPassword) {
        return Base64.getDecoder().decode(encodedPassword);
    }

}
