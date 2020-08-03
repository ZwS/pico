package com.asudak.pico.db.model;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public abstract class UserDTO {

    public abstract UUID getId();
    public abstract String getUsername();
}
