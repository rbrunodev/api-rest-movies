package ar.uba.fi.ingsoft1.cinegram.movies.dto;

import ar.uba.fi.ingsoft1.cinegram.actors.models.Actor;
import ar.uba.fi.ingsoft1.cinegram.categories.models.Category;
import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;

import java.util.Set;

public record MovieDTO(
        long id,
        String name,
        String description,
        Set<Actor> actors,
        Set<Category> categories
) {
    public MovieDTO(Movie movie) {
        this(movie.getId(), movie.getName(), movie.getDescription(), movie.getActors(), movie.getCategories());
    }
}
