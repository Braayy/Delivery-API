package io.github.braayy.Delivery.feature.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameIgnoreCaseContaining(String name, Pageable pageable);

}
