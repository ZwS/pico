package com.asudak.pico.nbi.server.user.model;

import com.asudak.pico.nbi.server.user.validation.PasswordConfirmation;

@PasswordConfirmation
public interface RepeatedPassword {

    String getPassword();

    String getRepeatPassword();
}
