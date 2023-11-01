package com.cmcba.api.controllers;

import com.cmcba.api.models.requests.LoginRequest;
import com.cmcba.api.models.requests.UserRequest;
import com.cmcba.api.models.responses.LoginResponse;
import com.cmcba.api.models.responses.UserResponse;
import com.cmcba.infrastructure.services.contracts.AuthService;
import com.cmcba.infrastructure.services.contracts.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author claudio.vilas
 * date 11/2023
 */

@RestController
@RequestMapping("/api/v1/cmcba/auth")
@SecurityRequirement(name = "Bearer Authentication")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "CMCBA - AUTH")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/create")
    @Operation(summary = "servicio alta de usuarios",
            description = "servicio alta de usuarios")
    @Parameter(name = "dto", description = "datos del usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest dto){
        log.info("#### endpoint crear usuario ####");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "servicio de authenticacion de usuarios",
            description = "servicio de authenticacion de usuarios")
    @Parameter(name = "dto", description = "datos del usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest dto){
        log.info("#### endpoint de authenticacion de usuario ####");
        return ResponseEntity.ok(authService.login(dto));
    }
}
