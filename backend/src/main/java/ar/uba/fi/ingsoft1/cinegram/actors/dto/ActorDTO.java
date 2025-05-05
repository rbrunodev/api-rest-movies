package ar.uba.fi.ingsoft1.cinegram.actors.dto;

import ar.uba.fi.ingsoft1.cinegram.actors.models.Actor;
import ar.uba.fi.ingsoft1.cinegram.movies.dto.MovieDTO;
import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;

import java.util.Set;

public record ActorDTO(
        long id,
        String title,
        String description,
        Set<Movie> movies
) {
    public ActorDTO(Actor actor) {
        this(actor.getId(), actor.getTitle(), actor.getDescription(), actor.getMovies());
    }
}
