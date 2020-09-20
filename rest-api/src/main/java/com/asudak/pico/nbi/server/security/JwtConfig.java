package com.asudak.pico.nbi.server.security;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.config.Names;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JwtConfig {

    @Inject
    @ConfigProperty(name = Names.ISSUER)
    String issuer;

    public String getIssuer() {
        return issuer;
    }
}
