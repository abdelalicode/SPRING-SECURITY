package com.secure.secureAPP.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.secure.secureAPP.user.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    CLIENT(Collections.emptySet()),
    ADMIN(
            Set.of(ADMIN_READ,ADMIN_CREATE, ADMIN_DELETE, ADMIN_UPDATE, EMPLOYEE_READ, EMPLOYEE_CREATE , EMPLOYEE_UPDATE, EMPLOYEE_DELETE)
    ),
    EMPLOYEE (
            Set.of(EMPLOYEE_READ, EMPLOYEE_CREATE , EMPLOYEE_UPDATE, EMPLOYEE_DELETE)
    );


    private final Set<Permission> permissions;


    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
