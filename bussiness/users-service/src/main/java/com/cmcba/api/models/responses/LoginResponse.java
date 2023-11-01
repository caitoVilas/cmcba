package com.cmcba.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author claudio.vilas
 * date 11/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginResponse implements Serializable {
    private String jwt;
    private UserResponse user;
}
