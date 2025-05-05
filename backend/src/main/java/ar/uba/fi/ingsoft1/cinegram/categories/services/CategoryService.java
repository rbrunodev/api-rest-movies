package ar.uba.fi.ingsoft1.cinegram.categories.services;

import ar.uba.fi.ingsoft1.cinegram.categories.dto.CategoryCreateDTO;
import ar.uba.fi.ingsoft1.cinegram.categories.dto.CategoryDTO;
import ar.uba.fi.ingsoft1.cinegram.categories.interfaces.CategoryRepository;
import ar.uba.fi.ingsoft1.cinegram.movies.dto.MovieCreateDTO;
import ar.uba.fi.ingsoft1.cinegram.movies.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<CategoryDTO> get(long id) {
        return categoryRepository.findById(id).map(CategoryDTO::new);
    }

    public Page<CategoryDTO> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(CategoryDTO::new);
    }

    public CategoryDTO create(CategoryCreateDTO create) {
        return new CategoryDTO(categoryRepository.save(create.asCategory()));
    }

    public Optional<CategoryDTO> update(long id, CategoryCreateDTO category) {
        if (!categoryRepository.existsById(id)) {
            return Optional.empty();
        }
        var saved = categoryRepository.save(category.asCategory(id));
        return Optional.of(new CategoryDTO(saved));
    }
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }
}
