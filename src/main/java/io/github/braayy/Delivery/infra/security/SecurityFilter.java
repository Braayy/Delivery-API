package io.github.braayy.Delivery.infra.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.braayy.Delivery.feature.user.UserRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        getRequestToken(request).ifPresent((token) -> {
            DecodedJWT jwt = this.tokenService.verifyToken(token);
            String subject = this.tokenService.getSubject(jwt);
            UserRole role = this.tokenService.getUserRole(jwt);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subject, null, List.of(role.getAuthority()));

            SecurityContextHolder.getContext().setAuthentication(auth);
        });

        filterChain.doFilter(request, response);
    }

    private Optional<String> getRequestToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .map((value) -> value.replace("Bearer ", ""));
    }
}
