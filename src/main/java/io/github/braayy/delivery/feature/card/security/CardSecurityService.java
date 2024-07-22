package io.github.braayy.delivery.feature.card.security;

import io.github.braayy.delivery.feature.card.Card;
import io.github.braayy.delivery.feature.card.CardService;
import io.github.braayy.delivery.feature.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardSecurityService {

    private final CardService cardService;

    public Long getCardOwnerId(Long userIdParam) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().contains(UserRole.CLIENT.getAuthority())) {
            return (Long) auth.getPrincipal();
        } else if (auth.getAuthorities().contains(UserRole.CASHIER.getAuthority())) {
            throw new BadCredentialsException("Users with role CASHIER cannot access card information of other users");
        } else if (auth.getAuthorities().contains(UserRole.ADMIN.getAuthority())) {
            if (userIdParam == null) {
                throw new IllegalArgumentException("Users with role ADMIN can only access card information for other users through the user query param");
            }

            return userIdParam;
        }

        throw new IllegalStateException("User has no role!");
    }

    public boolean canAccess(Authentication auth, Long cardId) {
        Long loggedInUserId = (Long) auth.getPrincipal();

        Card card = this.cardService.getById(cardId);

        return loggedInUserId.equals(card.getUser().getId());
    }

}
