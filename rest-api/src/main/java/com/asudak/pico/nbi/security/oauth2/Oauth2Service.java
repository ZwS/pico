package com.asudak.pico.nbi.security.oauth2;

import com.asudak.pico.core.security.PasswordService;
import com.asudak.pico.db.model.AccessTokenDTO;
import com.asudak.pico.db.model.ImmutableAccessTokenDTO;
import com.asudak.pico.db.model.ImmutableRefreshTokenDTO;
import com.asudak.pico.db.model.RefreshTokenDTO;
import com.asudak.pico.nbi.client.db.token.TokenServiceClient;
import com.asudak.pico.nbi.client.db.user.UserServiceClient;
import com.asudak.pico.nbi.security.GenerateSecurityTokenRequest;
import com.asudak.pico.nbi.security.RefreshTokenRequest;
import com.asudak.pico.nbi.security.SecurityResponse;
import com.asudak.pico.nbi.security.SecurityService;
import com.asudak.pico.nbi.security.jwt.JwtService;
import org.apache.commons.lang3.RandomStringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import static com.asudak.pico.nbi.client.ClientHelper.handlingNotFound;

@ApplicationScoped
public class Oauth2Service implements SecurityService {

    private final Oauth2Configuration oauth2Configuration;
    private final JwtService jwtService;
    private final UserServiceClient userService;
    private final TokenServiceClient tokenService;
    private final PasswordService passwordService;

    @Inject
    public Oauth2Service(Oauth2Configuration oauth2Configuration, JwtService jwtService, UserServiceClient userService,
            TokenServiceClient tokenService, PasswordService passwordService) {
        this.oauth2Configuration = oauth2Configuration;
        this.jwtService = jwtService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordService = passwordService;
    }

    @Override
    public SecurityResponse generateToken(@Valid GenerateSecurityTokenRequest request) {
        return handlingNotFound(() -> userService.getUser(request.getUsername(), passwordService.hash(request.getPassword())))
                .map(user -> {
                    String accessTokenString = generateSecret();
                    String refreshTokenString = generateSecret();
                    String jwt = jwtService.createToken(user, accessTokenString, oauth2Configuration.getTokenExpiresIn());
                    AccessTokenDTO accessToken = ImmutableAccessTokenDTO.builder().jwtSecret(jwt).token(accessTokenString)
                            .username(user.getUsername()).build();
                    RefreshTokenDTO refreshToken = ImmutableRefreshTokenDTO.builder().token(refreshTokenString)
                            .accessToken(accessToken).username(user.getUsername()).build();
                    tokenService.saveRefreshToken(refreshToken);
                    tokenService.saveAccessToken(accessToken);

                    return Oauth2Response.of(accessToken, refreshToken, oauth2Configuration.getTokenExpiresIn().toSeconds());
                })
                //TODO add negative SecurityResponse
                .orElseGet(() -> null);
    }

    @Override
    public SecurityResponse refreshToken(@Valid RefreshTokenRequest request) {
        return handlingNotFound(() -> tokenService.getRefreshToken(request.getRefreshToken()))
                .flatMap(refreshToken -> handlingNotFound(() -> userService.getUserDetails(refreshToken.getUserId()))
                        .map(user -> {
                            String token = generateSecret();
                            String jwt = jwtService.createToken(user, token, oauth2Configuration.getTokenExpiresIn());
                            AccessTokenDTO accessToken = ImmutableAccessTokenDTO.builder().jwtSecret(jwt).token(token).username(user.getUsername()).build();
                            tokenService.saveAccessToken(accessToken);

                            return Oauth2Response.of(accessToken, refreshToken, oauth2Configuration.getTokenExpiresIn().toSeconds());
                        }))
                    //TODO add negative SecurityResponse
                .orElseGet(() -> null);
    }

    private String generateSecret() {
        return RandomStringUtils.random(32, true, true);
    }
}
