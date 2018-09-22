/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app;


import com.hansa.app.repo.StudentRepo;
import com.hansa.app.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant Kumar
 */

@RestController
public class StudentResource {
    
    @Autowired
    private StudentRepo studentRepo;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/student", method = {RequestMethod.GET})
    public Iterable<Student> getTutors() {
        return studentRepo.findAll();
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/student", method = {RequestMethod.POST})
    public Student save(@RequestBody Student student) {
        return studentRepo.save(student);
    }
    
    
}
