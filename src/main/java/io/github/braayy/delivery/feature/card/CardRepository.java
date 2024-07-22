package io.github.braayy.delivery.feature.card;

import io.github.braayy.delivery.feature.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Page<Card> findAllByUser(User user, Pageable pageable);
}
