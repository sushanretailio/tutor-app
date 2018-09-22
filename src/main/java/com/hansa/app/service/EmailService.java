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

    @PostConstruct
    void setUp() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session = Session.getDefaultInstance(props);
        log.info("Mail session started");

    }

    private static final Log log = LogFactory.getFactory().getInstance(EmailService.class);
    private Session session;

    private final String username = "sushant001@gmail.com";
    private final String password = "12345";

    public void sendEmail(String body, String subject, String to) {
        log.info("Sending email to " + to + " , Subject " + subject + ", Body " + body);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(subject); 
            message.setText(body);
            Transport.send(message);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }

}
