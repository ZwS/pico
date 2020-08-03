package com.asudak.pico.nbi.server.user.model;

import com.asudak.pico.nbi.server.user.validation.PasswordConfirmation;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CreateUserRequest implements RepeatedPassword {

    @NotEmpty
    private final String username;
    @NotEmpty
    private final String password;
    @NotEmpty
    private final String repeatPassword;
    @Email
    private final String email;

    @JsonbCreator
    public CreateUserRequest(
            @JsonbProperty("username") String username,
            @JsonbProperty("password") String password,
            @JsonbProperty("repeatPassword") String repeatPassword,
            @JsonbProperty("email") String email
    ) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getEmail() {
        return email;
    }
}
