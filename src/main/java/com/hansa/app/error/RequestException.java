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

public class RequestException extends RuntimeException {
    
    private String message;

    public RequestException() {
    }

    public RequestException(String message) {
        super(message);
    }
    
}
