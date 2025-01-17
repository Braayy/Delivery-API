package io.github.braayy.delivery.feature.user;

import io.github.braayy.delivery.dto.ApiResponse;
import io.github.braayy.delivery.feature.user.dto.ListUserDTO;
import io.github.braayy.delivery.feature.user.dto.RegisterUserDTO;
import io.github.braayy.delivery.feature.user.dto.ShowUserDTO;
import io.github.braayy.delivery.feature.user.dto.UpdateUserDTO;
import io.github.braayy.delivery.feature.user.security.UserSecurityService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserSecurityService userSecurityService;

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<ShowUserDTO>> register(
        @Valid @RequestBody RegisterUserDTO body,
        UriComponentsBuilder uriBuilder
    ) {
        if (body.role() != UserRole.CLIENT) {
            this.userSecurityService.throwIsNotAdmin(new BadCredentialsException("Only users with role ADMIN can register users with role CASHIER or ADMIN"));
        }

        User user = this.userService.register(body);

        URI uri = uriBuilder
            .path("/users/{id}")
            .build(user.getId());

        return ResponseEntity.created(uri)
            .body(ApiResponse.data(new ShowUserDTO(user)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @userSecurityService.canAccess(authentication, #id)")
    public ResponseEntity<ApiResponse<ShowUserDTO>> show(
        @PathVariable Long id
    ) {
        User user = this.userService.getById(id);

        return ApiResponse.dataResponse(new ShowUserDTO(user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ListUserDTO>>> list(
        @PageableDefault Pageable pageable
    ) {
        Page<User> page = this.userService.listAll(pageable);

        return ApiResponse.dataResponse(page.map(ListUserDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or @userSecurityService.canAccess(authentication, #id)")
    public ResponseEntity<ApiResponse<ShowUserDTO>> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateUserDTO body
    ) {
        User user = this.userService.update(id, body);

        return ApiResponse.dataResponse(new ShowUserDTO(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or @userSecurityService.canAccess(authentication, #id)")
    public ResponseEntity<Void> delete(
        @PathVariable Long id
    ) {
        this.userService.deactivate(id);

        return ResponseEntity.noContent().build();
    }


}
