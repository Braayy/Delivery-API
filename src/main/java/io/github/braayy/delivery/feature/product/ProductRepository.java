package io.github.braayy.delivery.feature.product;

import io.github.braayy.delivery.feature.productgroup.ProductGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(
        """
            SELECT p
            FROM Product p
            WHERE (
                (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', CAST(:name AS text), '%'))) AND
                (:group IS NULL OR p.group = :group)
            )
        """
    )
    Page<Product> findByNameContainingIgnoreCaseAndGroup(String name, ProductGroup group, Pageable pageable);

}
