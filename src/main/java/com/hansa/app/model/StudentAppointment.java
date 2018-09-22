/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.model;

import com.hansa.app.data.Appointment;
import com.hansa.app.data.Tutor;

/**
 *
 * @author sushant
 */
public class StudentAppointment {
    
    private Tutor tutor;
    private Appointment appointment;

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    
    
    
}
