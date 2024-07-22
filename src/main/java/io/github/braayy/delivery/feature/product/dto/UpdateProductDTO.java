package io.github.braayy.delivery.feature.product.dto;

import io.github.braayy.delivery.feature.product.QuantityType;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public record UpdateProductDTO(
    @Size(min = 1, max = 50)
    String name,

    @Size(min = 1)
    String description,

    @URL
    @Size(min = 1)
    String imageUrl,

    @PositiveOrZero
    BigDecimal price,

    QuantityType quantityType,

    @Positive
    Integer weightMultiplier,

    @PositiveOrZero
    Short maxAdditionals
) {
}
