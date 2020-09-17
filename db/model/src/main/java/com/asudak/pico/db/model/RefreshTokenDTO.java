package com.asudak.pico.db.model;

import org.immutables.value.Value;

@Value.Immutable
public abstract class RefreshTokenDTO {

    public abstract AccessTokenDTO getAccessToken();
    public abstract String getToken();
    public abstract String getUserId();
}
