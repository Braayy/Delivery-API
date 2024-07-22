package io.github.braayy.delivery.feature.user.dto;

import io.github.braayy.delivery.feature.user.User;
import io.github.braayy.delivery.feature.user.UserRole;

public record ShowUserDTO(
    String name,
    String email,
    UserRole role,
    Boolean active
) {
    public ShowUserDTO(User user) {
        this(user.getName(), user.getEmail(), user.getRole(), user.getActive());
    }
}
