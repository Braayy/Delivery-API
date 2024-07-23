package io.github.braayy.delivery.feature.productadditional.dto;

import io.github.braayy.delivery.feature.productadditional.ProductAdditional;

import java.math.BigDecimal;

public record ShowProductAdditionalDTO(
    Long id,
    Long productId,
    String name,
    String description,
    BigDecimal price
) {
    public ShowProductAdditionalDTO(ProductAdditional productAdditional) {
        this(
            productAdditional.getId(),
            productAdditional.getProduct().getId(),
            productAdditional.getName(),
            productAdditional.getDescription(),
            productAdditional.getPrice()
        );
    }
}
