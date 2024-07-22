package io.github.braayy.delivery.feature.user;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum UserRole {
    CLIENT,
    CASHIER,
    ADMIN,
    ;

    private final SimpleGrantedAuthority authority;

    UserRole() {
        this.authority = new SimpleGrantedAuthority("ROLE_" + this.name());
    }
}
