package io.github.braayy.Delivery.feature.address;

import io.github.braayy.Delivery.feature.address.dto.RegisterAddressDTO;
import io.github.braayy.Delivery.feature.address.dto.UpdateAddressDTO;
import io.github.braayy.Delivery.feature.user.User;
import io.github.braayy.Delivery.feature.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    public Address register(RegisterAddressDTO dto, Long userId) {
        User user = this.userService.getById(userId);

        Address address = Address.builder()
            .user(user)
            .street(dto.street())
            .number(dto.number())
            .complement(dto.complement())
            .district(dto.district())
            .city(dto.city())
            .state(dto.state())
            .cep(dto.cep())
            .build();

        return this.addressRepository.save(address);
    }

    public Address getById(Long addressId) {
        return this.addressRepository.getReferenceById(addressId);
    }

    public Page<Address> listAll(Pageable pageable, Long userId) {
        User user = this.userService.getById(userId);

        return this.addressRepository.findAllByUser(user, pageable);
    }

    public Address update(Long addressId, UpdateAddressDTO dto) {
        Address address = this.addressRepository.getReferenceById(addressId);

        if (dto.street() != null) {
            address.setStreet(dto.street());
        }

        if (dto.number() != null) {
            address.setNumber(dto.number());
        }

        if (dto.complement() != null) {
            address.setComplement(dto.complement());
        }

        if (dto.district() != null) {
            address.setDistrict(dto.district());
        }

        if (dto.city() != null) {
            address.setCity(dto.city());
        }

        if (dto.state() != null) {
            address.setState(dto.state());
        }

        if (dto.cep() != null) {
            address.setCep(dto.cep());
        }

        return address;
    }

    public void deleteById(Long addressId) {
        this.addressRepository.deleteById(addressId);
    }

}
