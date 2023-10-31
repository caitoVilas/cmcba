package com.cmcba.api.exceptions.customs;

/**
 * @author claudio.vilas
 * date 10/2023
 */

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
