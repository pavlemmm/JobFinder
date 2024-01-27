package com.example.jobfinder.exceptions;

/**
 *
 * @author Korisnik
 */
public abstract class UserNotValidException extends Exception{

    public UserNotValidException(String message) {
        super(message);
    }
    
}
