package io.github.braayy.delivery.feature.card;

import io.github.braayy.delivery.dto.ApiResponse;
import io.github.braayy.delivery.feature.card.dto.ListCardDTO;
import io.github.braayy.delivery.feature.card.dto.RegisterCardDTO;
import io.github.braayy.delivery.feature.card.dto.ShowCardDTO;
import io.github.braayy.delivery.feature.card.dto.UpdateCardDTO;
import io.github.braayy.delivery.feature.card.security.CardSecurityService;
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
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final CardSecurityService cardSecurityService;

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<ShowCardDTO>> register(
        @Valid @RequestBody RegisterCardDTO body,
        @RequestParam(required = false) Long user,
        UriComponentsBuilder uriBuilder
    ) {
        Long ownerId = this.cardSecurityService.getCardOwnerId(user);

        Card card = this.cardService.register(body, ownerId);

        URI uri = uriBuilder
            .path("/cards/{id}")
            .build(card.getId());

        return ResponseEntity.created(uri)
            .body(ApiResponse.data(new ShowCardDTO(card)));
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @cardSecurityService.canAccess(authentication, #id)")
    public ResponseEntity<ApiResponse<ShowCardDTO>> show(
        @PathVariable Long id
    ) {
        Card card = this.cardService.getById(id);

        return ApiResponse.dataResponse(new ShowCardDTO(card));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ListCardDTO>>> list(
        @PageableDefault Pageable pageable,
        @RequestParam(required = false) Long user
    ) {
        Long ownerId = this.cardSecurityService.getCardOwnerId(user);

        Page<Card> page = this.cardService.listAll(pageable, ownerId);

        return ApiResponse.dataResponse(page.map(ListCardDTO::new));
    }
    
    @Transactional
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @cardSecurityService.canAccess(authentication, #id)")
    public ResponseEntity<ApiResponse<ShowCardDTO>> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateCardDTO body
    ) {
        Card card = this.cardService.update(id, body);

        return ApiResponse.dataResponse(new ShowCardDTO(card));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @cardSecurityService.canAccess(authentication, #id)")
    public ResponseEntity<ApiResponse<Void>> delete(
        @PathVariable Long id
    ) {
        this.cardService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
