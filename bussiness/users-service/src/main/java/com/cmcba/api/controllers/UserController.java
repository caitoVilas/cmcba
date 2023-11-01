package com.cmcba.api.controllers;

import com.cmcba.api.models.responses.UserResponse;
import com.cmcba.infrastructure.services.contracts.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author claudio.vilas
 * date 10/2023
 */

@RestController
@RequestMapping("/api/v1/cmcba/users")
@SecurityRequirement(name = "Bearer Authentication")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "CMCBA - USUARIOS")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "servicio buscar usuarios por id",
               description = "servicio buscar usuarios por id")
    @Parameter(name = "id", description = "id del usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        log.info("#### endpoint buscar usuario por id");
        return ResponseEntity.ok(userService.getById(id));
    }
}
