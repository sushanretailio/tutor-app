/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */

@Service
public class SequenceService {
    
    Calendar cal = GregorianCalendar.getInstance();
    public String getTutorSequence(Long id) {
        int year = cal.get(Calendar.YEAR);
        String sequence = "T/"+year+"/"+id;
        return sequence;
    }
    
    public String getStudentSequence(Long id) {
        int year = cal.get(Calendar.YEAR);
        String sequence = "S/"+year+"/"+id;
        return sequence;
    }
    
    public String getJobSequence(Long id) {
        int year = cal.get(Calendar.YEAR);
        String sequence = "J/"+year+"/"+id;
        return sequence;
    }
}
