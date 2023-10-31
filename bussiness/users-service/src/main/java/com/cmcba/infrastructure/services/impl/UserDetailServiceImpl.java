package com.cmcba.infrastructure.services.impl;

import com.cmcba.api.exceptions.customs.BadRequestException;
import com.cmcba.api.models.MainUser;
import com.cmcba.domain.repositories.UserRepository;
import com.cmcba.utils.constants.UserConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author claudio.vilas
 * date 10/2023
 */

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(()->{
            throw new BadRequestException(UserConstants.U_NAME_NOT_FOUND.concat(username));
        });
        return MainUser.build(user);
    }
}
