package com.asudak.pico.core.security;

import com.asudak.pico.core.exception.ApplicationException;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Singleton
public class PasswordService {

    public String hash(@NotNull String password) {
        MessageDigest digest;

        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new ApplicationException("SHA-256 is not supported.", e);
        }

        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }

    public boolean matches(@NotNull String hash, @NotNull String password) {
        return hash(password).equals(hash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
