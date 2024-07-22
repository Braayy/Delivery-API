package io.github.braayy.delivery.feature.user.dto;

import io.github.braayy.delivery.feature.user.User;
import io.github.braayy.delivery.feature.user.UserRole;

public record ListUserDTO(
    Long id,
    String name,
    String email,
    UserRole role
) {
    public ListUserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
