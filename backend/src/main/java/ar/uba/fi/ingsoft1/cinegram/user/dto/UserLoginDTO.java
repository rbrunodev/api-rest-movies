package ar.uba.fi.ingsoft1.cinegram.user.dto;

import ar.uba.fi.ingsoft1.cinegram.user.interfaces.UserCredentials;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(
        @NotBlank String username,
        @NotBlank String password
) implements UserCredentials {}
