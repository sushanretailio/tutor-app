/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import com.hansa.app.data.Job;
import com.hansa.app.data.Tutor;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */
@Service
public class MailUtil {
    
    
    
    @Autowired
    private EmailService emailService;

    private VelocityEngine ve;
    
     private static final Log log = LogFactory.getFactory().getInstance(MailUtil.class);

    @PostConstruct
    public void init() {
        ve = new VelocityEngine();
        ve.init();
    }

    @Async
    public void jobNotification(List<Tutor> tutorList, Job job) {
        log.info("Sending job posting email to "+tutorList.size()+" tutors");
        try {
            Template t = ve.getTemplate("job_post.vm");
            VelocityContext context = new VelocityContext();
            context.put("stdName", job.getStudent().getName());
            context.put("stdCls", job.getClassName());
            context.put("subject", job.getSubject());
            
            List<String> toList = new ArrayList<>();
            for(Tutor tt : tutorList) {
                toList.add(tt.getEmail());
            }
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            emailService.sendEmail(writer.toString(), "Hannsaa job posting", toList);
            
        } catch (Exception ex) {
            log.error(ex);
        }
        
    }
    
    
    
    @Async 
    public void tutorApply(Tutor tutor, Job job) {
        log.info("Sending job application email");
        try {
            Template t = ve.getTemplate("job_apply.vm");
            VelocityContext context = new VelocityContext();
            context.put("stdName", job.getStudent().getName());
            context.put("stdCls", job.getClassName());
            context.put("subject", job.getSubject());
            context.put("credit",tutor.getCredit());
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            emailService.sendEmail(writer.toString(), "Hannsaa job application", tutor.getEmail());
            
        } catch (Exception ex) {
            log.error(ex);
        }
        
    }
    
    @Async
    public void tutorRegister(Tutor tutor) {
        log.info("Sending tutor registration email");
        try {
            Template t = ve.getTemplate("tutor-register.vm");
            VelocityContext context = new VelocityContext();
            context.put("name", tutor.getName());
            context.put("mobile", tutor.getMobile());
            context.put("password", tutor.getMobile());
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            emailService.sendEmail(writer.toString(), "Hannsaa registration", tutor.getEmail());
            
        } catch (Exception ex) {
            log.error(ex);
        }
        
    }

}
