package io.github.braayy.Delivery.feature.address;

import io.github.braayy.Delivery.feature.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private User user;

    @Setter
    @NonNull
    private String street;

    @Setter
    private String number;

    @Setter
    private String complement;

    @Setter
    @NonNull
    private String district;

    @Setter
    @NonNull
    private String city;

    @Setter
    @NonNull
    private String state;

    @Setter
    @NonNull
    private String cep;

}
