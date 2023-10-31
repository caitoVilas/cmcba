package com.cmcba.utils.maps;

import com.cmcba.api.models.requests.UserRequest;
import com.cmcba.api.models.responses.UserResponse;
import com.cmcba.domain.entities.UserApplication;

import java.util.stream.Collectors;

/**
 * @author claudio.vilas
 * date 10/2023
 */

public class UserMap {
    public static UserResponse mapToDto(UserApplication entity){
        return UserResponse.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .telephone(entity.getTelephone())
                .createAt(entity.getCreteAt())
                .updatedAt(entity.getUpdatedAt())
                .roles(entity.getRoles().stream().map(RoleMap::mapToDto).collect(Collectors.toSet()))
                .build();
    }
}
