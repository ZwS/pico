package com.asudak.pico.db.model;

import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
public abstract class UserDetailsDTO extends UserDTO {

    public abstract Set<RoleDTO> getRoles();
}
