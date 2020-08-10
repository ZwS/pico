package com.asudak.pico.nbi.server.user.validation;

import com.asudak.pico.nbi.server.user.model.RepeatedPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, RepeatedPassword> {

    @Override
    public boolean isValid(RepeatedPassword value, ConstraintValidatorContext context) {
        return Objects.equals(value.getPassword(), value.getRepeatPassword());
    }
}
