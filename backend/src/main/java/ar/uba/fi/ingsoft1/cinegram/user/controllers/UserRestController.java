package ar.uba.fi.ingsoft1.cinegram.user.controllers;

import ar.uba.fi.ingsoft1.cinegram.user.services.UserService;
import ar.uba.fi.ingsoft1.cinegram.user.dto.TokenDTO;
import ar.uba.fi.ingsoft1.cinegram.user.dto.UserCreateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "1 - Users")
class UserRestController {
    private final UserService userService;

    @Autowired
    UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    @Operation(summary = "Inscribirse")
    ResponseEntity<TokenDTO> singUp(
            @Valid @RequestBody UserCreateDTO data
    ) {
        return userService.singUp(data)
                .map(tk -> ResponseEntity.status(HttpStatus.CREATED).body(tk))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
