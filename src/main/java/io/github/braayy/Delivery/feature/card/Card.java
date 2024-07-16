package io.github.braayy.Delivery.feature.card;

import io.github.braayy.Delivery.feature.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Card {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Setter
    @NonNull
    private String brand;

    @Setter
    @NonNull
    private String number;

    @Setter
    @NonNull
    private String holderName;

    @Setter
    @NonNull
    private String holderCpf;

    @Setter
    @NonNull
    private LocalDate expirationDate;

    @Setter
    @NonNull
    private String cvv;

}
