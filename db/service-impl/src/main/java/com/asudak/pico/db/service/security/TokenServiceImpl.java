package com.asudak.pico.db.service.security;

import com.asudak.pico.db.mapper.AccessTokenMapper;
import com.asudak.pico.db.mapper.RefreshTokenMapper;
import com.asudak.pico.db.model.AccessTokenDTO;
import com.asudak.pico.db.model.RefreshTokenDTO;
import com.asudak.pico.db.repository.TokenRepository;
import com.asudak.pico.db.service.NotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;
    private final AccessTokenMapper accessTokenMapper;

    @Inject
    public TokenServiceImpl(TokenRepository tokenRepository, RefreshTokenMapper refreshTokenMapper, AccessTokenMapper accessTokenMapper) {
        this.tokenRepository = tokenRepository;
        this.refreshTokenMapper = refreshTokenMapper;
        this.accessTokenMapper = accessTokenMapper;
    }

    @Override
    public void saveAccessToken(AccessTokenDTO token) {
        Optional.of(token).map(accessTokenMapper::toEntity)
                .ifPresent(tokenRepository::save);
    }

    @Override
    public void saveRefreshToken(RefreshTokenDTO token) {
        Optional.of(token).map(refreshTokenMapper::toEntity)
                .ifPresent(tokenRepository::save);
    }

    @Override
    public AccessTokenDTO getAccessToken(String token) {
        return tokenRepository.findAccessToken(token)
                .map(accessTokenMapper::toDTO)
                .orElseThrow(() -> NotFoundException.builder(AccessTokenDTO.class).field("token").value(token).build());
    }

    @Override
    public RefreshTokenDTO getRefreshToken(String token) {
        return tokenRepository.findRefreshToken(token)
                .map(refreshTokenMapper::toDTO)
                .orElseThrow(() -> NotFoundException.builder(RefreshTokenDTO.class).field("token").value(token).build());
    }
}
