package com.cmcba.infrastructure.services.contracts;

import com.cmcba.api.models.requests.LoginRequest;
import com.cmcba.api.models.responses.LoginResponse;

/**
 * @author claudio.vilas
 * date 11/2023
 */

public interface AuthService {

    LoginResponse login(LoginRequest dto);
}
