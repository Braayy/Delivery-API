package io.github.braayy.delivery.feature.address.dto;

import io.github.braayy.delivery.feature.address.Address;

public record ShowAddressDTO(
    String street,
    String number,
    String complement,
    String district,
    String city,
    String state,
    String cep
) {
    public ShowAddressDTO(Address address) {
        this(
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
