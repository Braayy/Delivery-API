package io.github.braayy.delivery.feature.card.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record RegisterCardDTO(
    @Size(min = 1, max = 30)
    String nickname,

    @NotBlank
    @Size(max = 20)
    String brand,

    @NotBlank
    @CreditCardNumber
    @Size(max = 16)
    String number,

    @NotBlank
    @Size(max = 100)
    String holderName,

    @NotBlank
    @CPF
    @Size(max = 100)
    String holderCpf,

    @NotNull
    @Future
    LocalDate expirationDate,

    @NotBlank
    @Size(min = 3, max = 3)
    String cvv
) {
}
