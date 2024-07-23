package io.github.braayy.delivery.feature.productadditional;

import io.github.braayy.delivery.feature.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_additionals")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class ProductAdditional {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private Product product;

    @Setter
    @NonNull
    private String name;

    @Setter
    @NonNull
    private String description;


    @Setter
    @NonNull
    private BigDecimal price;
}
