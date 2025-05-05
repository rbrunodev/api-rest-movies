package ar.uba.fi.ingsoft1.cinegram.actors.dto;

import ar.uba.fi.ingsoft1.cinegram.actors.models.Actor;
import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;
import ar.uba.fi.ingsoft1.todo_template.common.exception.ItemNotFoundException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.LongFunction;

public record ActorCreateDTO(
        @NotBlank @Size(min = 1, max = 100) String title,
        @Size(min = 1, max = 100) String description,
        List<Long> moviesId
) {
    public Actor asActor(LongFunction<Optional<Movie>> movieFetcher) throws ItemNotFoundException {
        return this.asActor(null, movieFetcher);
    }

    public Actor asActor(Long id, LongFunction<Optional<Movie>> movieFetcher) throws ItemNotFoundException {
        Set<Movie> movies = new HashSet<>();

        if (moviesId != null) {
            for (Long movieId : moviesId) {
                Movie movie = movieFetcher.apply(movieId)
                        .orElseThrow(() -> new ItemNotFoundException("movie", movieId));
                movies.add(movie);
            }
        }

        return new Actor(id, title, description, movies);
    }
}
