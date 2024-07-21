package io.github.braayy.Delivery.feature.card.dto;

import io.github.braayy.Delivery.feature.card.Card;

import java.time.LocalDate;

public record ListCardDTO(
    Long id,
    String nickname,
    String brand,
    String number,
    String holderName,
    String holderCpf,
    LocalDate expirationDate
) {
    public ListCardDTO(Card card) {
        this(
            card.getId(),
            card.getNickname(),
            card.getBrand(),
            card.getNumber(),
            card.getHolderName(),
            card.getHolderCpf(),
            card.getExpirationDate()
        );
    }
}
