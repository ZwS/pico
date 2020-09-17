package com.asudak.pico.nbi.security;

import javax.validation.Valid;

public interface SecurityService {

    SecurityResponse generateToken(GenerateSecurityTokenRequest request);

    SecurityResponse refreshToken(@Valid RefreshTokenRequest request);
}
