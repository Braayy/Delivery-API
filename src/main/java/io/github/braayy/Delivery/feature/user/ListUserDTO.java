package io.github.braayy.Delivery.feature.user;

public record ListUserDTO(
    Long id,
    String name,
    String email,
    UserRole role
) {
    public ListUserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
