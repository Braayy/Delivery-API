package io.github.braayy.delivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private List<String> errors;
    private T data;

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(List.of(message), null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> errorResponse(HttpStatusCode status, String message) {
        return ResponseEntity.status(status)
                .body(new ApiResponse<>(List.of(message), null));
    }

    public static <T> ApiResponse<T> error(List<String> messages) {
        return new ApiResponse<>(messages, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> errorResponse(HttpStatusCode status, List<String> messages) {
        return ResponseEntity.status(status)
            .body(new ApiResponse<>(messages, null));
    }

    public static <T> ApiResponse<T> data(T data) {
        return new ApiResponse<>(null, data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> dataResponse(T data) {
        return ResponseEntity.ok(new ApiResponse<>(null, data));
    }

}
