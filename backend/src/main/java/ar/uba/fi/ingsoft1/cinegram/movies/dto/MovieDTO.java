package ar.uba.fi.ingsoft1.cinegram.movies.dto;

import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;

public record MovieDTO(
        long id,
        String name,
        String description
) {
    public MovieDTO(Movie movie) {
        this(movie.getId(), movie.getName(), movie.getDescription());
    }
}
