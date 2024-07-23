package io.github.braayy.delivery.feature.productgroup;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_groups")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class ProductGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NonNull
    private String name;

}
