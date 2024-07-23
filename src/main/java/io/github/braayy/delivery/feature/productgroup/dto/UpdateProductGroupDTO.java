package io.github.braayy.delivery.feature.productgroup.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductGroupDTO(
    @NotNull
    @Size(min = 1, max = 50)
    String name
) {
}
