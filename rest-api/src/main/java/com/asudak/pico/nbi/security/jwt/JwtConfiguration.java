package com.asudak.pico.nbi.security.jwt;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class JwtConfiguration {

    private final String jwtIssuer;

    @Inject
    public JwtConfiguration(
            @ConfigProperty(name = "pico.security.jwt.issuer", defaultValue = "pico") String jwtIssuer
    ) {
        this.jwtIssuer = jwtIssuer;
    }

    public String getJwtIssuer() {
        return jwtIssuer;
    }
}
