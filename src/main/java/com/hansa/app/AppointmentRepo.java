/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */

public interface AppointmentRepo extends CrudRepository<Appointment, Long> {
    
    
    @Query("select a from Appointment a where a.studentId =:studentId ")
    List<Appointment> getByStudent(@Param("studentId") Long studentId) ;
    
    @Query("select a from Appointment a where a.tutorId =:tutorId ")
    List<Appointment> getByTutor(@Param("tutorId") Long tutorId) ;
    
}
