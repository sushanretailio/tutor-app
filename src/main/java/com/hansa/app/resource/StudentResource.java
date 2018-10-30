/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;


import com.hansa.app.repo.StudentRepo;
import com.hansa.app.data.Student;
import com.hansa.app.data.User;
import com.hansa.app.repo.ReviewRepo;
import com.hansa.app.repo.UserRepo;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class StudentResource {
    
    @Autowired
    private StudentRepo studentRepo;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private ReviewRepo reviewRepo;
    
    
    
    @RequestMapping(method = {RequestMethod.GET})
    public Iterable<Student> getTutors() {
        return studentRepo.findAll();
    }
    
    @RequestMapping("/{id}")
    public Student get(@PathVariable("id") Long id) {
        Student std = studentRepo.getById(id);
        std.setReviews(reviewRepo.getByStudent(id));
        Student empty = new Student();
        empty.setName(std.getName());
        std.getReviews().forEach(it-> it.setStudent(empty));
        return std;
    }
    
    
    @RequestMapping(method = {RequestMethod.POST})
    public User save(@RequestBody Student student) {
        if(student.getMobile()==null || student.getMobile().isEmpty()) {
            throw new RuntimeException("Mobile number cant be empty");
        }
        Student std= studentRepo.save(student);
        User user = new User();
        user.setRefId(std.getId());
        user.setType("STUDENT");
        user.setUserId(std.getMobile());
        user.setPassword(std.getMobile());
        userRepo.save(user);
        user.setDetail(std);
        return user;
    }
}
