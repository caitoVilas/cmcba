package com.cmcba.api.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author claudio.vilas
 * date 10/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String telephone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate createAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate updatedAt;
    private Set<RoleResponse> roles;
}
