package com.cmcba.api.controllers;

import com.cmcba.api.models.responses.UserResponse;
import com.cmcba.infrastructure.services.contracts.UserService;
import com.cmcba.utils.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("#### endpoint buscar usuario por id ####");
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    @Operation(summary = "servicio buscar usuarios",
            description = "servicio buscar usuarios")
    @Parameters({
            @Parameter(name = "page", description = "pagina a mostrar"),
            @Parameter(name = "size", description = "cantidad de elementos"),
            @Parameter(name = "sortType", description = "ordenamiento")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<Page<UserResponse>> getAll(@RequestParam int page,
                                                     @RequestParam int size,
                                                     @RequestParam SortType sortType){
        log.info("#### endpoint para mostrar usuarios ####");
        var response = userService.getAll(page, size, sortType);
        if (response.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "servicio eliminar usuarios por id",
            description = "servicio eliminar usuarios por id")
    @Parameter(name = "id", description = "id del usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<UserResponse> delete(@PathVariable Long id){
        log.info("#### endpoint eliminar usuario por id ####");
        return ResponseEntity.ok(userService.getById(id));
    }
}
