package com.asudak.pico.nbi.server.user.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = PasswordConfirmationValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface PasswordConfirmation {

    String message() default "{pico.validation.PasswordConfirmation.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
