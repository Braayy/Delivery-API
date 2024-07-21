package io.github.braayy.Delivery.feature.address;

import io.github.braayy.Delivery.dto.ApiResponse;
import io.github.braayy.Delivery.feature.address.dto.ListAddressDTO;
import io.github.braayy.Delivery.feature.address.dto.RegisterAddressDTO;
import io.github.braayy.Delivery.feature.address.dto.ShowAddressDTO;
import io.github.braayy.Delivery.feature.address.dto.UpdateAddressDTO;
import io.github.braayy.Delivery.feature.address.security.AddressSecurityService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final AddressSecurityService addressSecurityService;

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<ShowAddressDTO>> register(
        @Valid @RequestBody RegisterAddressDTO body,
        @RequestParam(required = false) Long user,
        UriComponentsBuilder uriBuilder
    ) {
        Long ownerId = this.addressSecurityService.getAddressOwnerId(user);

        Address address = this.addressService.register(body, ownerId);

        URI uri = uriBuilder
            .path("/address/{id}")
            .build(address.getId());

        return ResponseEntity.created(uri)
            .body(ApiResponse.data(new ShowAddressDTO(address)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @addressSecurityService.canAccess(authentication, #id)")
    public ResponseEntity<ApiResponse<ShowAddressDTO>> show(
        @PathVariable Long id
    ) {
        Address address = this.addressService.getById(id);

        return ApiResponse.dataResponse(new ShowAddressDTO(address));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ListAddressDTO>>> list(
        @PageableDefault Pageable pageable,
        @RequestParam(required = false) Long user
    ) {
        Long ownerId = this.addressSecurityService.getAddressOwnerId(user);

        Page<Address> page = this.addressService.listAll(pageable, ownerId);

        return ApiResponse.dataResponse(page.map(ListAddressDTO::new));
    }

    @Transactional
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @addressSecurityService.canAccess(authentication, #id)")
    public ResponseEntity<ApiResponse<ShowAddressDTO>> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateAddressDTO body
    ) {
        Address address = this.addressService.update(id, body);

        return ApiResponse.dataResponse(new ShowAddressDTO(address));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @addressSecurityService.canAccess(authentication, #id)")
    public ResponseEntity<ApiResponse<Void>> delete(
        @PathVariable Long id
    ) {
        this.addressService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
