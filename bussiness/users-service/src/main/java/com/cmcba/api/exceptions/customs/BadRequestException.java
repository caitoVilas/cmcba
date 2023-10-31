package com.cmcba.api.exceptions.customs;

/**
 * @author claudio.vilas
 * date 10/2023
 */

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
