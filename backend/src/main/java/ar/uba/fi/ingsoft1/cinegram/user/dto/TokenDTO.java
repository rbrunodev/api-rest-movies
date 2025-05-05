package ar.uba.fi.ingsoft1.cinegram.user.dto;

import jakarta.validation.constraints.NotNull;

public record TokenDTO(
        @NotNull String accessToken,
        String refreshToken
) {
}
