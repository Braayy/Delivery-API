package io.github.braayy.Delivery.feature.product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NonNull
    private String name;

    @Setter
    private String description;

    @Setter
    private String imageUrl;

    @Setter
    @NonNull
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Setter
    @NonNull
    private QuantityType quantityType;

    @Setter
    private Integer weightMultiplier;

    @Setter
    @NonNull
    private Short maxAdditionals;

}
