package com.asudak.pico.nbi.security.oauth2;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class Oauth2Configuration {

    private final Duration tokenExpiresIn;

    @Inject
    public Oauth2Configuration(
            @ConfigProperty(name = "pico.security.jwt.expires_in", defaultValue = "3600") int tokenExpiresIn
    ) {
        this.tokenExpiresIn = Duration.of(tokenExpiresIn, ChronoUnit.SECONDS);
    }

    public Duration getTokenExpiresIn() {
        return tokenExpiresIn;
    }
}
