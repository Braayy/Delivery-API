package io.github.braayy.Delivery.infra;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.MissingClaimException;
import io.github.braayy.Delivery.dto.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionBeautifier {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> generic(Exception exception) {
        exception.printStackTrace();

        return ApiResponse.errorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> notFound(EntityNotFoundException exception) {
        exception.printStackTrace();

        return ApiResponse.errorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler({AuthenticationException.class, JWTCreationException.class})
    public ResponseEntity<ApiResponse<Void>> authentication(Exception exception) {
        exception.printStackTrace();

        return ApiResponse.errorResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> validation(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getFieldErrors().stream()
            .map((error) -> "\"%s\" is invalid: \"%s\"".formatted(error.getField(), error.getDefaultMessage()))
            .toList();

        return ApiResponse.errorResponse(HttpStatus.BAD_REQUEST, errors);
    }

}
