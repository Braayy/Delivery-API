package io.github.braayy.Delivery.feature.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterAddressDTO(
    @NotBlank
    @Size(max = 100)
    String street,

    @Size(min = 1, max = 20)
    String number,

    @Size(min = 1, max = 20)
    String complement,

    @NotBlank
    @Size(max = 100)
    String district,

    @NotBlank
    @Size(max = 100)
    String city,

    @NotBlank
    @Size(max = 2)
    String state,

    @NotBlank
    @Size(max = 9)
    String cep
) {}