package io.github.braayy.Delivery.feature.user.dto;

import io.github.braayy.Delivery.feature.user.User;
import io.github.braayy.Delivery.feature.user.UserRole;

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
