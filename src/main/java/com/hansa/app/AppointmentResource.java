/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant Kumar
 */

@RestController
public class AppointmentResource {
    
    @Autowired
    private AppointmentRepo appointmentRepo;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/appointment/student/{id}", method = {RequestMethod.GET})
    public Iterable<Appointment> appointForStudent(@PathVariable("id") Long id) {
        return appointmentRepo.getByStudent(id);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/appointment/tutor/{id}", method = {RequestMethod.GET})
    public Iterable<Appointment> appointForTutor(@PathVariable("id") Long id) {
        return appointmentRepo.getByTutor(id);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/appointment", method = {RequestMethod.POST})
    public Appointment save(@RequestBody Appointment appointment) {
        appointment.setStatus("CREATED");
        appointment.setDate(new Date());
        return appointmentRepo.save(appointment);
    }
    
    
}
