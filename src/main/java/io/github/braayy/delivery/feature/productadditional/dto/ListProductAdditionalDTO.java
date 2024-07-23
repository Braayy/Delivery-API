package io.github.braayy.delivery.feature.productadditional.dto;

import io.github.braayy.delivery.feature.productadditional.ProductAdditional;

import java.math.BigDecimal;

public record ListProductAdditionalDTO(
    Long id,
    Long productId,
    String name,
    BigDecimal price
) {
    public ListProductAdditionalDTO(ProductAdditional productAdditional) {
        this(
            productAdditional.getId(),
            productAdditional.getProduct().getId(),
            productAdditional.getName(),
            productAdditional.getPrice()
        );
    }
}