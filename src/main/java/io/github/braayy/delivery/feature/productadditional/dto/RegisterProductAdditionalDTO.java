package io.github.braayy.delivery.feature.productadditional.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record RegisterProductAdditionalDTO(
    @NotNull
    Long productId,

    @NotBlank
    @Size(max = 50)
    String name,

    @NotBlank
    String description,

    @NotNull
    @PositiveOrZero
    BigDecimal price
) {
}
