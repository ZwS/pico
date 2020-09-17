package com.asudak.pico.nbi.server.security.model;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotEmpty;

public class RefreshTokenRequest {

    @NotEmpty
    private final String refreshToken;

    @JsonbCreator
    public RefreshTokenRequest(@JsonbProperty("refreshToken") String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
