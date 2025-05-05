package ar.uba.fi.ingsoft1.cinegram.categories.dto;

import ar.uba.fi.ingsoft1.cinegram.categories.models.Category;

import java.util.Locale;

public record CategoryDTO(
        long id,
        String name,
        String description)
{
    public CategoryDTO(Category category){
        this(category.getId(), category.getName(), category.getDescription());
    }
}
