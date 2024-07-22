package io.github.braayy.delivery.feature.address.security;

import io.github.braayy.delivery.feature.address.Address;
import io.github.braayy.delivery.feature.address.AddressService;
import io.github.braayy.delivery.feature.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressSecurityService {

    private final AddressService addressService;

    public Long getAddressOwnerId(Long userIdParam) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().contains(UserRole.CLIENT.getAuthority())) {
            return (Long) auth.getPrincipal();
        } else if (auth.getAuthorities().contains(UserRole.CASHIER.getAuthority())) {
            throw new BadCredentialsException("Users with role CASHIER cannot access addresses of other users");
        } else if (auth.getAuthorities().contains(UserRole.ADMIN.getAuthority())) {
            if (userIdParam == null) {
                throw new IllegalArgumentException("Users with role ADMIN can only access addresses for other users through the user query param");
            }

            return userIdParam;
        }

        throw new IllegalStateException("User has no role!");
    }

    public boolean canAccess(Authentication auth, Long addressId) {
        Long loggedInUserId = (Long) auth.getPrincipal();

        Address address = this.addressService.getById(addressId);

        return loggedInUserId.equals(address.getUser().getId());
    }

}
