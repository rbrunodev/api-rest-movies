package ar.uba.fi.ingsoft1.cinegram.actors.dto;

import ar.uba.fi.ingsoft1.cinegram.actors.models.Actor;
import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;
import ar.uba.fi.ingsoft1.todo_template.common.exception.ItemNotFoundException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Optional;
import java.util.function.LongFunction;

public record ActorCreateDTO(
        @NotBlank @Size(min = 1, max = 100) String title,
        @Size(min = 1, max = 100) String description,
        @Positive Long projectId
) {
    public Actor asActor(LongFunction<Optional<Movie>> getProject) throws ItemNotFoundException {
        return this.asActor(null, getProject);
    }

    public Actor asActor(Long id, LongFunction<Optional<Movie>> getProject) throws ItemNotFoundException {
        var project = projectId == null
                ? null
                : getProject.apply(projectId).orElseThrow(() -> new ItemNotFoundException("project", projectId));
        return new Actor(id, title, description, project);
    }
}
