/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */

@Service
public class EmailService {
    
    private static final Log log =  LogFactory.getFactory().getInstance(EmailService.class);
    
    
    private String sender = "";
    private String password = "";
    
    public void sendEmail(String body, String subject, String to) {
        log.info("Sending email to "+to+" , Subject "+subject+", Body "+body);
        
    }
    
}
