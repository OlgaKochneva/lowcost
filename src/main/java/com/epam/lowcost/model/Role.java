package com.epam.lowcost.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

    public static final Set<Role> userRole = new HashSet<Role>() {{
        add(ROLE_USER);
    }};
    public static final Set<Role> adminRole = new HashSet<Role>() {{
        add(ROLE_USER);
        add(ROLE_ADMIN);
    }};
}
