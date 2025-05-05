package ar.uba.fi.ingsoft1.cinegram.movies.dto;

import ar.uba.fi.ingsoft1.cinegram.actors.models.Actor;
import ar.uba.fi.ingsoft1.cinegram.categories.models.Category;
import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ar.uba.fi.ingsoft1.todo_template.common.exception.ItemNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.LongFunction;

public record MovieCreateDTO(
        @NotBlank @Size(min = 1, max = 100) String name,
        @Size(min = 1, max = 100) String description,
        List<Long> actorIds,
        List<Long> categoryIds
) {
    public Movie asMovie(LongFunction<Optional<Category>> getCategory, LongFunction<Optional<Actor>> getActor) throws ItemNotFoundException {
        return asMovie(null, getCategory, getActor);
    }

    public Movie asMovie(Long id, LongFunction<Optional<Category>> getCategory, LongFunction<Optional<Actor>> getActor) throws ItemNotFoundException {
        Set<Category> categories = new HashSet<>();

        if (categoryIds != null) {
            for (Long categoryId : categoryIds) {
                Category category = getCategory.apply(categoryId)
                        .orElseThrow(() -> new ItemNotFoundException("category", categoryId));
                categories.add(category);
            }
        }

        Movie movie = new Movie(id, this.name, this.description, categories);

        if (actorIds != null && !actorIds.isEmpty()) {
            Set<Actor> actors = new HashSet<>();
            for (Long actorId : actorIds) {
                Actor actor = getActor.apply(actorId)
                        .orElseThrow(() -> new ItemNotFoundException("actor", actorId));
                actors.add(actor);
            }
            movie.setActors(actors);
        }

        return movie;
    }
}
