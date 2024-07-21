package io.github.braayy.Delivery.feature.card.dto;

import io.github.braayy.Delivery.feature.card.Card;

import java.time.LocalDate;

public record ShowCardDTO(
    String nickname,
    String brand,
    String number,
    String holderName,
    String holderCpf,
    LocalDate expirationDate
) {
    public ShowCardDTO(Card card) {
        this(
            card.getNickname(),
            card.getBrand(),
            card.getNumber(),
            card.getHolderName(),
            card.getHolderCpf(),
            card.getExpirationDate()
        );
    }
}
