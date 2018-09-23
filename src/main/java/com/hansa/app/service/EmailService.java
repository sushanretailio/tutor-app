/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */
@Service
public class EmailService {
    
    private final String username = "mailer.hansaa@gmail.com";
    private final String password = "Oct@2018";


    @PostConstruct
    void setUp() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mailer.hansaa@gmail.com", "Oct@2018");
            }
        });
        log.info("Mail session started");
    }

    private static final Log log = LogFactory.getFactory().getInstance(EmailService.class);
    private Session session;

    
    public void sendEmail(String body, String subject, String to) {
        log.info("Sending email to " + to + " , Subject " + subject + ", Body " + body);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(subject); 
            message.setText(body);
            Transport.send(message);
            log.info("Mail sent");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }

    }

}
