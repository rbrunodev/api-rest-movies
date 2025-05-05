package ar.uba.fi.ingsoft1.cinegram.config.security;

public record JwtUserDetails (
        String username,
        String role
) {}