package com.cmcba.infrastructure.services.impl;

import com.cmcba.api.models.requests.LoginRequest;
import com.cmcba.api.models.responses.LoginResponse;
import com.cmcba.components.JwtProvider;
import com.cmcba.domain.repositories.UserRepository;
import com.cmcba.infrastructure.services.contracts.AuthService;
import com.cmcba.infrastructure.services.contracts.UserService;
import com.cmcba.utils.maps.UserMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public LoginResponse login(LoginRequest dto) {
        log.info("---> inicio servicio login");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        var user = userRepository.findByUsername(dto.getUsername()).get();
        log.info("---> login ok");
        return LoginResponse.builder()
                .jwt(token)
                .user(UserMap.mapToDto(user))
                .build();
    }
}
