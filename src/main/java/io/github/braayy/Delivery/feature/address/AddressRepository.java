package io.github.braayy.Delivery.feature.address;

import io.github.braayy.Delivery.feature.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Page<Address> findAllByUser(User user, Pageable pageable);

}
