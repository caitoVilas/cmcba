package com.cmcba.infrastructure.services.contracts;

import com.cmcba.api.models.requests.UserRequest;
import com.cmcba.api.models.responses.UserResponse;
import com.cmcba.utils.enums.SortType;
import org.springframework.data.domain.Page;

/**
 * @author claudio.vilas
 * date 10/2023
 */

public interface UserService {
    UserResponse create(UserRequest request);
    UserResponse getById(Long id);
    Page<UserResponse> getAll(int Page, int size, SortType sortType);
    void delete(Long id);
}
