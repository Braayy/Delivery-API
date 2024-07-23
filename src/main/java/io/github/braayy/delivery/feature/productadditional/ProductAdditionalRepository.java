package io.github.braayy.delivery.feature.productadditional;

import io.github.braayy.delivery.feature.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductAdditionalRepository extends JpaRepository<ProductAdditional, Long> {

    List<ProductAdditional> findAllByProduct(Product product);

}
