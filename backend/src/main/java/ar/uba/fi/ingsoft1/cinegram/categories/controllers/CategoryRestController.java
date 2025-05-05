package ar.uba.fi.ingsoft1.cinegram.categories.controllers;

import ar.uba.fi.ingsoft1.cinegram.categories.dto.CategoryCreateDTO;
import ar.uba.fi.ingsoft1.cinegram.categories.dto.CategoryDTO;
import ar.uba.fi.ingsoft1.cinegram.categories.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category")
public class CategoryRestController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get a list of categories")
    @ResponseStatus(HttpStatus.OK)
    Page<CategoryDTO> get(
            @Valid @ParameterObject Pageable pageable
    ) throws MethodArgumentNotValidException {
        return categoryService.getAll(pageable);
    }

    @PostMapping(produces = "application/json")
    @Operation(summary = "Create a new category")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    CategoryDTO create(
            @Valid @RequestBody CategoryCreateDTO categoryCreate
    ) throws MethodArgumentNotValidException {
        return categoryService.create(categoryCreate);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a category by its id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    ResponseEntity<CategoryDTO> getCategory(
            @Valid @PathVariable @Positive long id
    ) throws MethodArgumentNotValidException {
        return ResponseEntity.of(categoryService.get(id));
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Update a category")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    ResponseEntity<CategoryDTO> put(
            @Valid @PathVariable @Positive long id,
            @Valid @RequestBody CategoryCreateDTO categoryCreate
    ) throws MethodArgumentNotValidException {
        return ResponseEntity.of(categoryService.update(id, categoryCreate));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a category")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(
            @Valid @PathVariable @Positive long id
    ) throws MethodArgumentNotValidException {
        categoryService.delete(id);
    }
}
