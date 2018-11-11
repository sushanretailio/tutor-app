/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.error;

/**
 *
 * @author sushant
 */

public class UnAuthoriseException extends RuntimeException {
    
    private String message;

    public UnAuthoriseException() {
    }

    public UnAuthoriseException(String message) {
        super(message);
    }
    
}
