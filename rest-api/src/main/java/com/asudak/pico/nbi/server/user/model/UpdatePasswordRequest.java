package com.asudak.pico.nbi.server.user.model;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotEmpty;

public class UpdatePasswordRequest implements RepeatedPassword {

    @NotEmpty
    private final String password;
    @NotEmpty
    private final String repeatPassword;

    @JsonbCreator
    public UpdatePasswordRequest(
            @JsonbProperty("password")  String password,
            @JsonbProperty("repeatPassword") String repeatPassword
    ) {
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRepeatPassword() {
        return repeatPassword;
    }
}
