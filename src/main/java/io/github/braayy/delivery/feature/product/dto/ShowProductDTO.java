package io.github.braayy.delivery.feature.product.dto;

import io.github.braayy.delivery.feature.product.Product;
import io.github.braayy.delivery.feature.product.QuantityType;

import java.math.BigDecimal;

public record ShowProductDTO(
    String name,
    String description,
    String imageUrls,
    BigDecimal price,
    QuantityType quantityType,
    Integer weightMultiplier,
    Short maxAdditionals
) {
    public ShowProductDTO(Product product) {
        this(
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
