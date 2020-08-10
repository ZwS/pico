package com.asudak.pico.core.security;

import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

@Singleton
public class PasswordService {

    public String hash(@NotNull String password) {
        return DigestUtils.sha256Hex(password);
    }

    public boolean matches(@NotNull String hash, @NotNull String password) {
        return hash(password).equals(hash);
    }
}
