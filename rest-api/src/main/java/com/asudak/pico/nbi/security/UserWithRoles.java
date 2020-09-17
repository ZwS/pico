package com.asudak.pico.nbi.security;

import java.security.Principal;
import java.util.Set;

public class UserWithRoles implements Principal {

    private final String username;
    private final Set<String> roles;

    public UserWithRoles(String username, Set<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    @Override
    public String getName() {
        return this.username;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
