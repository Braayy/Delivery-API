package io.github.braayy.Delivery.utils;

import java.util.Optional;

public final class EnumUtils {

    public static <T extends Enum<T>> Optional<T> valueOf(Class<T> enumClass, String name) {
        try {
            return Optional.of(Enum.valueOf(enumClass, name));
        } catch (IllegalArgumentException exception) {
            return Optional.empty();
        }
    }
}
