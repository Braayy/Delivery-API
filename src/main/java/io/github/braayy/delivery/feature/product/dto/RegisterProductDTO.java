package io.github.braayy.delivery.feature.product.dto;

import io.github.braayy.delivery.feature.product.QuantityType;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public record RegisterProductDTO(
    @NotBlank
    @Size(max = 50)
    String name,

    @Size(min = 1)
    String description,

    @NotBlank
    @URL
    String imageUrl,

    @NotNull
    @PositiveOrZero
    BigDecimal price,

    @NotNull
    QuantityType quantityType,

    @Positive
    Integer weightMultiplier,

    @NotNull
    @PositiveOrZero
    Short maxAdditionals
) {
}
