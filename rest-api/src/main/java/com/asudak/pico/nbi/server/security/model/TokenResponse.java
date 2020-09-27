package com.asudak.pico.nbi.server.security.model;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class TokenResponse {

    private final String token;

    @JsonbCreator
    public TokenResponse(@JsonbProperty("token") String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
