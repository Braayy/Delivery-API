package io.github.braayy.delivery.feature.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
    @Size(min = 1, max = 50)
    String name,

    @Size(min = 1, max = 100)
    @Email
    String email,

    @Size(min = 1)
    String password
) {
}
