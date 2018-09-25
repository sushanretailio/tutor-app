/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.User;
import com.hansa.app.repo.StudentRepo;
import com.hansa.app.repo.TutorRepo;
import com.hansa.app.repo.UserRepo;
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
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private StudentRepo studentRepo;
    
    @Autowired
    private TutorRepo tutorRepo;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(method=RequestMethod.POST)
    public User login(@RequestBody User user) {
        User foundUser = userRepo.get(user.getUserId(), user.getPassword());
        if(foundUser==null) throw new RuntimeException("User not found.");
        if(foundUser.getType().equals("STUDENT")) {
            foundUser.setDetail(studentRepo.getById(foundUser.getRefId()));
        } else {
            foundUser.setDetail(tutorRepo.getById(foundUser.getRefId()));
        }
        return foundUser;
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public User changePassword(@RequestBody User user) {
        User foundUser = userRepo.getOne(user.getId());
        if(foundUser==null) throw new RuntimeException("User not found.");
        foundUser.setPassword(user.getPassword());
        return userRepo.save(foundUser);
    }
    
}
