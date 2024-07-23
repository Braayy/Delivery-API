package io.github.braayy.delivery.feature.productadditional.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProductAdditionalDTO(
    @Size(min = 1, max = 50)
    String name,

    @Size(min = 1)
    String description,

    @PositiveOrZero
    BigDecimal price
) {
}
