package io.github.braayy.delivery.feature.address.dto;

import io.github.braayy.delivery.feature.address.Address;

public record ListAddressDTO(
    Long id,
    String street,
    String number,
    String complement,
    String district,
    String city,
    String state,
    String cep
) {
    public ListAddressDTO(Address address) {
        this(
            address.getId(),
            address.getStreet(),
            address.getNumber(),
            address.getComplement(),
            address.getDistrict(),
            address.getCity(),
            address.getState(),
            address.getCep()
        );
    }
}
