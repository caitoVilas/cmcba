package com.cmcba.api.models.responses;

import com.cmcba.utils.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author claudio.vilas
 * date 10/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoleResponse implements Serializable {
    private Long id;
    private RoleName roleName;
}
