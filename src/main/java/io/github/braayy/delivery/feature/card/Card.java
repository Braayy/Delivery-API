package io.github.braayy.delivery.feature.card;

import io.github.braayy.delivery.feature.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Card {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private User user;

    @Setter
    private String nickname;

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
