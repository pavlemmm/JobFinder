/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jobfinder.exceptions;

/**
 *
 * @author Korisnik
 */
public class PasswordNotValidException extends UserNotValidException {

    public PasswordNotValidException(String message) {
        super(message);
    }

}