package io.github.braayy.delivery.feature.user.security;

import io.github.braayy.delivery.feature.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService {

    public void throwIsNotAdmin(RuntimeException exception) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getAuthorities().contains(UserRole.ADMIN.getAuthority())) {
            throw exception;
        }
    }

    public boolean canAccess(Authentication auth, Long userId) {
        Long loggedInUserId = (Long) auth.getPrincipal();

        return loggedInUserId.equals(userId);
    }

}
