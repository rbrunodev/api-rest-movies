package ar.uba.fi.ingsoft1.cinegram.movies.dto;

import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MovieCreateDTO(
        @NotBlank @Size(min = 1, max = 100) String name,
        @Size(min = 1, max = 100) String description
) {
    public Movie asMovie() {
        return this.asMovie(null);
    }

    public Movie asMovie(Long id) {
        return new Movie(id, this.name, this.description);
    }
}
