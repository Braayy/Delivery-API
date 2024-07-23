package io.github.braayy.delivery.feature.productgroup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterProductGroupDTO(
    @NotBlank
    @Size(max = 50)
    String name
) {}