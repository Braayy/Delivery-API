package io.github.braayy.delivery.feature.productgroup.dto;

import io.github.braayy.delivery.feature.productgroup.ProductGroup;

public record ShowProductGroupDTO(
    String name
) {
    public ShowProductGroupDTO(ProductGroup productGroup) {
        this(
            productGroup.getName()
        );
    }
}