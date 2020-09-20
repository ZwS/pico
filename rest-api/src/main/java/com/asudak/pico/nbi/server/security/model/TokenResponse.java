package com.asudak.pico.nbi.server.security.model;

public class TokenResponse {

    private final String token;

    private TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public static TokenResponse of(String token) {
        return new TokenResponse(token);
    }
}
