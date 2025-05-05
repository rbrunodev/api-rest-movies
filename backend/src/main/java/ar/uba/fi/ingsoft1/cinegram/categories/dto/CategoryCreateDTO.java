package ar.uba.fi.ingsoft1.cinegram.categories.dto;

import ar.uba.fi.ingsoft1.cinegram.categories.models.Category;
import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryCreateDTO(
        @NotBlank @Size(min = 1, max = 100) String name,
        @Size(min = 1, max = 100) String description
) {
    public Category asCategory() {
        return this.asCategory(null);
    }

    public Category asCategory(Long id) {
        return new Category(id, this.name, this.description);
    }
}
