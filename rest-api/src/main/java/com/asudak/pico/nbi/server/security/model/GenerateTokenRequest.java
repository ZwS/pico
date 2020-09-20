package com.asudak.pico.nbi.server.security.model;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotEmpty;

public class GenerateTokenRequest {

    @NotEmpty
    private final String username;
    @NotEmpty
    private final String password;

    @JsonbCreator
    public GenerateTokenRequest(
            @JsonbProperty("username") String username,
            @JsonbProperty("password") String password
    ) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
