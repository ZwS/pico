package com.asudak.pico.nbi.security.oauth2;

import com.asudak.pico.db.model.AccessTokenDTO;
import com.asudak.pico.db.model.RefreshTokenDTO;
import com.asudak.pico.nbi.security.SuccessfulSecurityResponse;

public class Oauth2Response implements SuccessfulSecurityResponse {

    private final String accessToken;
    private final String refreshToken;
    private final long expiresIn;

    private Oauth2Response(String accessToken, String refreshToken, long expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public static Oauth2Response of(AccessTokenDTO accessToken, RefreshTokenDTO refreshToken, long expiresIn) {
        return new Oauth2Response(accessToken.getToken(), refreshToken.getToken(), expiresIn);
    }
}
