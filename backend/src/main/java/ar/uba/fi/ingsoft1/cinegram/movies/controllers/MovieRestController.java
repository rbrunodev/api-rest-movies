package ar.uba.fi.ingsoft1.cinegram.movies.controllers;

import ar.uba.fi.ingsoft1.cinegram.movies.dto.MovieCreateDTO;
import ar.uba.fi.ingsoft1.cinegram.movies.dto.MovieDTO;
import ar.uba.fi.ingsoft1.cinegram.movies.services.MovieService;
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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies")
public class MovieRestController {
    private final MovieService movieService;

    @Autowired
    MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/search",produces = "application/json")
    @Operation(summary = "Get a list of movies")
    @ResponseStatus(HttpStatus.OK)
    Page<MovieDTO> getMovies(
            @Valid @ParameterObject Pageable pageable,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) long actorId,
            @RequestParam(required = false) long categoryId
    ) throws MethodArgumentNotValidException {
        return movieService.getMovies(pageable, title, actorId, categoryId);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a movie by its id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
    ResponseEntity<MovieDTO> getMovie(
            @Valid @PathVariable @Positive long id
    ) throws MethodArgumentNotValidException {
        return ResponseEntity.of(movieService.getMovie(id));
    }

    @PostMapping(produces = "application/json")
    @Operation(summary = "Create a new movie")
    @ResponseStatus(HttpStatus.CREATED)
    MovieDTO createMovie(
            @Valid @RequestBody MovieCreateDTO movieCreate
    ) throws MethodArgumentNotValidException {
        return movieService.createMovie(movieCreate);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Update a movie")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
    ResponseEntity<MovieDTO> putMovie(
            @Valid @PathVariable @Positive long id,
            @Valid @RequestBody MovieCreateDTO movieCreate
    ) throws MethodArgumentNotValidException {
        return ResponseEntity.of(movieService.updateMovie(id, movieCreate));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a movie")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteMovie(
            @Valid @PathVariable @Positive long id
    ) throws MethodArgumentNotValidException {
        movieService.deleteMovie(id);
    }
}
