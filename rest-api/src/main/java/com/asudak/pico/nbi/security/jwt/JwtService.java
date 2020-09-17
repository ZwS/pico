package com.asudak.pico.nbi.security.jwt;

import com.asudak.pico.db.model.UserDetailsDTO;
import com.asudak.pico.nbi.security.UserWithRoles;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JwtService {

    private static final String ROLES = "roles";
    private final JwtConfiguration jwtConfiguration;

    @Inject
    public JwtService(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    public Optional<UserWithRoles> parseToken() {
        return Optional.empty();
    }

    public String createToken(UserDetailsDTO user, String secret, Duration expiresIn) {
        LocalDateTime expire = LocalDateTime.now(ZoneOffset.UTC).plus(expiresIn);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withJWTId(user.getUsername())
                .withIssuer(jwtConfiguration.getJwtIssuer())
                .withExpiresAt(Date.from(expire.atZone(ZoneOffset.UTC).toInstant()))
                .withClaim(ROLES, List.copyOf(user.getRoles()))
                .sign(algorithm);
    }
}
