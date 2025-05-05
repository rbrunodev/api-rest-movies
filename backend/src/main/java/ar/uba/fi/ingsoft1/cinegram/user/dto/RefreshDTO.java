package ar.uba.fi.ingsoft1.cinegram.user.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshDTO(
        @NotBlank String refreshToken
) {}
