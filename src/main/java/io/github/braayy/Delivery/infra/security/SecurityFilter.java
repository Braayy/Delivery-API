package io.github.braayy.Delivery.infra.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.braayy.Delivery.dto.ApiResponse;
import io.github.braayy.Delivery.feature.user.UserRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            getRequestToken(request).ifPresent((token) -> {
                DecodedJWT jwt = this.tokenService.verifyToken(token);
                String subject = this.tokenService.getSubject(jwt);
                UserRole role = this.tokenService.getUserRole(jwt);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subject, null, List.of(role.getAuthority()));

                SecurityContextHolder.getContext().setAuthentication(auth);
            });

            filterChain.doFilter(request, response);
        } catch (JWTVerificationException exception) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            PrintWriter pWriter = response.getWriter();

            String body = this.mapper.writeValueAsString(ApiResponse.error(exception.getMessage()));

            pWriter.write(body);
        }
    }

    private Optional<String> getRequestToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .map((value) -> value.replace("Bearer ", ""));
    }
}
