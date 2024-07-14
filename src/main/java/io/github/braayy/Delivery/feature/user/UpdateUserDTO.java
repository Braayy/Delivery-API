package io.github.braayy.Delivery.feature.user;

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
