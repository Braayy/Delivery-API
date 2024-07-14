package io.github.braayy.Delivery.feature.user;

public record ShowUserDTO(
    String name,
    String email,
    UserRole role,
    Boolean active
) {
    public ShowUserDTO(User user) {
        this(user.getName(), user.getEmail(), user.getRole(), user.getActive());
    }
}
