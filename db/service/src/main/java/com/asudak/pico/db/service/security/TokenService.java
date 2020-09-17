package com.asudak.pico.db.service.security;

import com.asudak.pico.db.model.AccessTokenDTO;
import com.asudak.pico.db.model.RefreshTokenDTO;

public interface TokenService {

    void saveAccessToken(AccessTokenDTO token);

    void saveRefreshToken(RefreshTokenDTO token);

    AccessTokenDTO getAccessToken(String token);

    RefreshTokenDTO getRefreshToken(String token);
}
