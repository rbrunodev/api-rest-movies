package ar.uba.fi.ingsoft1.cinegram.user.dto;

import ar.uba.fi.ingsoft1.cinegram.user.interfaces.UserCredentials;
import ar.uba.fi.ingsoft1.cinegram.user.models.User;
import jakarta.validation.constraints.NotBlank;

import java.util.function.Function;

public record UserCreateDTO(
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotBlank String fechaDeNacimiento,
        @NotBlank String genero,
        @NotBlank String fotoDePerfil
) implements UserCredentials {
    public User asUser(Function<String, String> encryptPassword) {
        return new User(
                email,
                encryptPassword.apply(password),
                nombre,
                apellido,
                fechaDeNacimiento,
                genero,
                fotoDePerfil
        );
    }

    @Override
    public String username() {
        return email;
    }
}