package io.github.braayy.Delivery.feature.user.dto;

import io.github.braayy.Delivery.feature.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserDTO(
    @Size(max = 50)
    @NotBlank
    String name,

    @Size(max = 100)
    @NotBlank
    @Email
    String email,

    @NotBlank
    String password,

    @NotNull
    UserRole role
) {
}
