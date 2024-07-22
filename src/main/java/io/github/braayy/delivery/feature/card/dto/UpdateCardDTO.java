package io.github.braayy.delivery.feature.card.dto;

import jakarta.validation.constraints.Size;

public record UpdateCardDTO(
    @Size(min = 1, max = 30)
    String nickname,

    @Size(max = 100)
    String holderName
) {
}
