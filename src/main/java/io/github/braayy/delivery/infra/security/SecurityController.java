package io.github.braayy.delivery.infra.security;

import io.github.braayy.delivery.dto.ApiResponse;
import io.github.braayy.delivery.feature.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class SecurityController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<ApiResponse<AuthenticationSuccessDTO>> login(
        @Valid @RequestBody LoginDTO body
    ) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.email(), body.password()));
        User user = ((User) authentication.getPrincipal());
        String token = this.tokenService.generateToken(user);

        return ApiResponse.dataResponse(new AuthenticationSuccessDTO(token));
    }

}
