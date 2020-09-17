package com.asudak.pico.db.model;

import org.immutables.value.Value;

@Value.Immutable
public abstract class AccessTokenDTO {

    public abstract String getJwtSecret();
    public abstract String getToken();
    public abstract String getUserId();
}
