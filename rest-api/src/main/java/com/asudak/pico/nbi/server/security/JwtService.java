package com.asudak.pico.nbi.server.security;

import com.asudak.pico.db.model.RoleDTO;
import com.asudak.pico.db.model.UserDetailsDTO;
import io.smallrye.jwt.build.Jwt;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class JwtService {

    @Inject
    JwtConfig jwtConfig;

    public String generateToken(UserDetailsDTO userDetails) {
        return Jwt.issuer(jwtConfig.getIssuer())
                .upn(userDetails.getUsername())
                .groups(userDetails.getRoles().stream().map(RoleDTO::name).collect(Collectors.toSet()))
                // TODO Add more claims here
                .sign();
    }
}
