package ar.uba.fi.ingsoft1.cinegram.actors.dto;

import ar.uba.fi.ingsoft1.cinegram.actors.models.Actor;
import ar.uba.fi.ingsoft1.cinegram.movies.dto.MovieDTO;

public record ActorDTO(
        long id,
        String title,
        String description,
        MovieDTO movie
) {
    public ActorDTO(Actor actor) {
        this(actor.getId(), actor.getTitle(), actor.getDescription(), new MovieDTO(actor.getProject()));
    }
}
