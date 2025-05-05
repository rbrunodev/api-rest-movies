package ar.uba.fi.ingsoft1.cinegram.actors.controllers;

import ar.uba.fi.ingsoft1.cinegram.actors.dto.ActorCreateDTO;
import ar.uba.fi.ingsoft1.cinegram.actors.dto.ActorDTO;
import ar.uba.fi.ingsoft1.cinegram.actors.services.ActorService;
import ar.uba.fi.ingsoft1.todo_template.common.exception.ItemNotFoundException;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actors")
@Tag(name = "Actors")
class ActorRestController {
    private final ActorService actorService;

    @Autowired
    ActorRestController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get a list of actors")
    @ResponseStatus(HttpStatus.OK)
    Page<ActorDTO> getActors(
            @Valid @ParameterObject Pageable pageable
    ) throws MethodArgumentNotValidException {
        return actorService.getActors(pageable);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a actor by its id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "404", description = "Actor not found", content = @Content)
    ActorDTO getActor(
            @Valid @PathVariable @Positive long id
    ) throws MethodArgumentNotValidException, ItemNotFoundException {
        return actorService.getActor(id);
    }

    @PostMapping(produces = "application/json")
    @Operation(summary = "Create a new actor")
    @ResponseStatus(HttpStatus.CREATED)
    ActorDTO createActor(
            @Valid @RequestBody ActorCreateDTO actorCreate
    ) throws MethodArgumentNotValidException, ItemNotFoundException {
        return actorService.createActor(actorCreate);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Update a actor")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "404", description = "Actor not found", content = @Content)
    ResponseEntity<ActorDTO> putActor(
            @Valid @PathVariable @Positive long id,
            @Valid @RequestBody ActorCreateDTO actorCreate
    ) throws MethodArgumentNotValidException, ItemNotFoundException {
        return ResponseEntity.of(actorService.updateActor(id, actorCreate));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a actor")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteActor(
            @Valid @PathVariable @Positive long id
    ) throws MethodArgumentNotValidException {
        actorService.deleteActor(id);
    }
}
