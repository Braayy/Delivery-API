package io.github.braayy.delivery.feature.product.dto;

import io.github.braayy.delivery.feature.product.Product;
import io.github.braayy.delivery.feature.product.QuantityType;

import java.math.BigDecimal;

public record ListProductDTO(
    Long id,
    String name,
    String description,
    String imageUrl,
    BigDecimal price,
    QuantityType quantityType,
    Integer weightMultiplier,
    Short maxAdditionals
) {
    public ListProductDTO(Product product) {
        this(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getImageUrl(),
            product.getPrice(),
            product.getQuantityType(),
            product.getWeightMultiplier(),
            product.getMaxAdditionals()
        );
    }
}