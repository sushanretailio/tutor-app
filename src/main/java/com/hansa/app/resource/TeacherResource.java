/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;


import com.hansa.app.repo.TutorRepo;
import com.hansa.app.data.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant
 */

@RestController
public class TeacherResource {
    
    @Autowired
    private TutorRepo tutorRepo;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/tutor", method = {RequestMethod.GET})
    public Iterable<Tutor> getTutors() {
        return tutorRepo.findAll();
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/tutor", method = {RequestMethod.POST})
    public Tutor save(@RequestBody Tutor tutor) {
        return tutorRepo.save(tutor);
    }
    
    
}
