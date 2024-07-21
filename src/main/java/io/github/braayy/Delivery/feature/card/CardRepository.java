package io.github.braayy.Delivery.feature.card;

import io.github.braayy.Delivery.feature.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Page<Card> findAllByUser(User user, Pageable pageable);
}
