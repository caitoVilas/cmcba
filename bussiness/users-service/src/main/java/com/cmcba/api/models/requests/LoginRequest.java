package com.cmcba.api.models.requests;

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
public class LoginRequest implements Serializable {
    private String username;
    private String password;
}
