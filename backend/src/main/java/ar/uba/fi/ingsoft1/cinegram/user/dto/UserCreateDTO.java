package ar.uba.fi.ingsoft1.cinegram.user.dto;

import ar.uba.fi.ingsoft1.cinegram.user.interfaces.UserCredentials;
import ar.uba.fi.ingsoft1.cinegram.user.models.User;
import jakarta.validation.constraints.NotBlank;

import java.util.function.Function;

public record UserCreateDTO(
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String name,
        @NotBlank String lastName,
        @NotBlank String birthdate,
        @NotBlank String gender,
        @NotBlank String avatar
) implements UserCredentials {
    public User asUser(Function<String, String> encryptPassword) {
        return new User(
                email,
                encryptPassword.apply(password),
                name,
                lastName,
                birthdate,
                gender,
                avatar
        );
    }

    @Override
    public String username() {
        return email;
    }
}