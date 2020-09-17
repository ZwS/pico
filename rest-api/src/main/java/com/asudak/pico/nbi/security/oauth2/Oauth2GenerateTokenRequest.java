package com.asudak.pico.nbi.security.oauth2;

import com.asudak.pico.nbi.security.GenerateSecurityTokenRequest;

import javax.validation.constraints.NotEmpty;

public class Oauth2GenerateTokenRequest implements GenerateSecurityTokenRequest {

    @NotEmpty
    private final String username;
    @NotEmpty
    private final String password;

    public Oauth2GenerateTokenRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
