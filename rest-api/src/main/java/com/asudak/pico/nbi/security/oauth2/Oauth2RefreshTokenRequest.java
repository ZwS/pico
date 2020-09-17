package com.asudak.pico.nbi.security.oauth2;

import javax.validation.constraints.NotEmpty;

public class Oauth2RefreshTokenRequest {

    @NotEmpty
    private final String refreshToken;

    public Oauth2RefreshTokenRequest(@NotEmpty String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
