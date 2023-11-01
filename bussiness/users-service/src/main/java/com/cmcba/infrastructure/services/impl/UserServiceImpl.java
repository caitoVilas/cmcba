package com.cmcba.infrastructure.services.impl;

import com.cmcba.api.exceptions.customs.BadRequestException;
import com.cmcba.api.exceptions.customs.NotFoundException;
import com.cmcba.api.models.requests.UserRequest;
import com.cmcba.api.models.responses.UserResponse;
import com.cmcba.domain.entities.Role;
import com.cmcba.domain.entities.UserApplication;
import com.cmcba.domain.repositories.RoleRepository;
import com.cmcba.domain.repositories.UserRepository;
import com.cmcba.infrastructure.services.contracts.UserService;
import com.cmcba.utils.constants.UserConstants;
import com.cmcba.utils.enums.RoleName;
import com.cmcba.utils.enums.SortType;
import com.cmcba.utils.maps.UserMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author claudio.vilas
 * date 10/2023
 */

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse create(UserRequest request) {
        log.info("---> inicio servicio alta de usuario");
        log.info("---> validando entradas...");
        this.validateUser(request);
        log.info("---> guardando usurio...");
        var role = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        var user = UserApplication.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .roles(roles)
                .build();
        var userNew = userRepository.save(user);
        return UserMap.mapToDto(userNew);
    }

    @Override
    public UserResponse getById(Long id) {
        log.info("---> inicio servicio buscar usuario por id");
        log.info("---> buscando usuario con id {}", id);
        var user = userRepository.findById(id).orElseThrow(()-> {
            log.error("ERROR: ".concat(UserConstants.U_ID_NOT_FOUND).concat(id.toString()));
            return new  NotFoundException(UserConstants.U_ID_NOT_FOUND.concat(id.toString()));
        });
        log.info("---> finalizado servicio alta de usuario");
        return UserMap.mapToDto(user);
    }

    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sortType) {
        log.info("---> inicio servicio consultar usuarios");
        if (size < 0) size = 0;
        if (page < 0) page = 5;
        PageRequest pr = null;
        log.info("---> buscando usuarios...");
        switch (sortType){
            case LOWER -> pr = PageRequest.of(page, size, Sort.by("username").ascending());
            case UPPER -> pr = PageRequest.of(page, size, Sort.by("username").descending());
            case NONE -> pr = PageRequest.of(page, size);
        }
        log.info("---> finalizado servicio consultar usuarios");
        return userRepository.findAll(pr).map(UserMap::mapToDto);
    }

    @Override
    public void delete(Long id) {
        log.info("---> inicio servicio eliminar usuario");
        log.info("---> eliminado usuario con id {}", id);
        userRepository.findById(id).orElseThrow(()-> {
            log.error("ERROR: ".concat(UserConstants.U_ID_NOT_FOUND).concat(id.toString()));
            return new  NotFoundException(UserConstants.U_ID_NOT_FOUND.concat(id.toString()));
        });
        userRepository.deleteById(id);
        log.info("---> finalizado servicio eliminar usuario");
    }

    private void validateUser(UserRequest request){
        if (request.getUsername().isBlank()){
            log.error("ERROR: ".concat(UserConstants.U_NAME_EMPTY));
            throw new BadRequestException(UserConstants.U_NAME_EMPTY);
        }

        if (userRepository.existsByUsername(request.getUsername())){
            log.error("ERROR: ".concat(UserConstants.U_NAME_EXISTS).concat(request.getUsername()));
            throw new BadRequestException(UserConstants.U_NAME_EXISTS.concat(request.getUsername()));
        }

        if (request.getPassword().isBlank()){
            log.error(UserConstants.U_PASS_EMPTY);
            throw new BadRequestException(UserConstants.U_PASS_EMPTY);
        }
        if (request.getEmail().isBlank()){
            log.error("ERROR: ".concat(UserConstants.U_EMAIL_EMPTY));
            throw new BadRequestException(UserConstants.U_EMAIL_EMPTY);
        }
        if (userRepository.existsByEmail(request.getEmail())){
            log.error("ERROR: ".concat(UserConstants.U_EMAIL_EXISTS.concat(request.getEmail())));
            throw new BadRequestException(UserConstants.U_EMAIL_EXISTS.concat(request.getEmail()));
        }
        if (!this.validateEmail(request.getEmail())){
            log.error("ERROR: ".concat(UserConstants.U_EMAIL_NO_VALID));
            throw new BadRequestException(UserConstants.U_EMAIL_NO_VALID);
        }
    }

    private boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            log.info("---> mail valido");
            return true;
        }else{
            log.info("ERROR:  mail invalido");
            return false;
        }
    }
}
