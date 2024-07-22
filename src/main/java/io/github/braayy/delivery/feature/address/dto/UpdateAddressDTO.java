package io.github.braayy.delivery.feature.address.dto;

import jakarta.validation.constraints.Size;

public record UpdateAddressDTO(
    @Size(min = 1, max = 100)
    String street,

    @Size(min = 1, max = 20)
    String number,

    @Size(min = 1, max = 20)
    String complement,

    @Size(min = 1, max = 100)
    String district,

    @Size(min = 1, max = 100)
    String city,

    @Size(min = 1, max = 2)
    String state,

    @Size(min = 1, max = 9)
    String cep
) {
}
