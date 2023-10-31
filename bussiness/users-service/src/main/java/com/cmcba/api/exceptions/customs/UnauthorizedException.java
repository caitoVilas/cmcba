package com.cmcba.api.exceptions.customs;

/**
 * @author claudio.vilas
 * date 10/2023
 */

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
