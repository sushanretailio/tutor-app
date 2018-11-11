/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.Tutor;
import java.io.StringWriter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 *
 * @author sushant
 */
public class Test {
    
    public static void main(String[] args) throws Exception {
        Tutor tt = new Tutor();
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        tt.setName("Sushant");
        tt.setEmail("sushant001@gmail.com");
        Template t = ve.getTemplate("tutor-register.vm");
            VelocityContext context = new VelocityContext();
            context.put("name", tt.getName());
            context.put("mobile", tt.getMobile());
            context.put("password", tt.getMobile());
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            System.out.println(writer.toString());
    }
    
}
