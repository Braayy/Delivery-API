package io.github.braayy.Delivery.infra.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.braayy.Delivery.feature.user.User;
import io.github.braayy.Delivery.feature.user.UserRole;
import io.github.braayy.Delivery.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.issuer}")
    private String issuer;

    public String generateToken(User user) {
        Algorithm algo = Algorithm.HMAC256(secret);
        return JWT.create()
            .withIssuer(this.issuer)
            .withSubject(user.getId().toString())
            .withClaim("role", user.getRole().name())
            .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
            .sign(algo);
    }

    public DecodedJWT verifyToken(String token) {
        Algorithm algo = Algorithm.HMAC256(secret);
        return JWT.require(algo)
            .withIssuer(this.issuer)
            .build()
            .verify(token);
    }

    public Long getSubject(DecodedJWT jwt) {
        String subjectString = jwt.getSubject();
        if (subjectString == null) {
            throw new MissingClaimException("Received JWT doesn't claim a subject!");
        }

        return Long.valueOf(subjectString);
    }

    public UserRole getUserRole(DecodedJWT jwt) {
        Claim roleClaim = jwt.getClaim("role");

        if (roleClaim.isMissing() || roleClaim.isNull()) {
            throw new MissingClaimException("Received JWT doesn't claim a user role!");
        }

        return Optional.ofNullable(roleClaim.asString())
                .flatMap((role) -> EnumUtils.valueOf(UserRole.class, role))
                .orElseThrow(() -> new InvalidClaimException("Received JWT role claim is not valid!"));
    }

}
