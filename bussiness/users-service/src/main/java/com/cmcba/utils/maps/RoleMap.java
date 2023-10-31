package com.cmcba.utils.maps;

import com.cmcba.api.models.responses.RoleResponse;
import com.cmcba.domain.entities.Role;

/**
 * @author claudio.vilas
 * date 10/2023
 */

public class RoleMap {
    public static RoleResponse mapToDto(Role role){
        return RoleResponse.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }
}
