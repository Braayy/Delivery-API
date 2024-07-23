package io.github.braayy.delivery.feature.productgroup.dto;

import io.github.braayy.delivery.feature.productgroup.ProductGroup;

public record ListProductGroupDTO(
    Long id,
    String name
) {
    public ListProductGroupDTO(ProductGroup productGroup) {
        this(
            productGroup.getId(),
            productGroup.getName()
        );
    }
}