/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.User;
import com.hansa.app.error.RequestException;
import com.hansa.app.repo.AdminRepo;
import com.hansa.app.repo.StudentRepo;
import com.hansa.app.repo.TutorRepo;
import com.hansa.app.repo.UserRepo;
import com.hansa.app.service.EmailService;
import com.hansa.app.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant
 */

@CrossOrigin(origins = "*" , methods = {RequestMethod.GET,RequestMethod.PATCH,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private StudentRepo studentRepo;
    
    @Autowired
    private TutorRepo tutorRepo;
    
    @Autowired
    private AdminRepo adminRepo;
    
    @Autowired
    private EmailService emailService;
    
    @CrossOrigin(origins = "*")
    @RequestMapping(method=RequestMethod.POST)
    public User login(@RequestBody User user) {
        User foundUser = userRepo.get(user.getUserId(), user.getPassword());
        if(foundUser==null) throw new RequestException("User not found.");
        
        if(!foundUser.getActive()) {
            throw new RequestException("User not not active.");
        }
        if(foundUser.getType().equals("STUDENT")) {
            foundUser.setDetail(studentRepo.getById(foundUser.getRefId()));
        } else if(foundUser.getType().equals("TUTOR")){
            foundUser.setDetail(tutorRepo.getById(foundUser.getRefId()));
        } else if(foundUser.getType().equals("ADMIN")){
            foundUser.setDetail(adminRepo.getById(foundUser.getRefId()));
        } else {
          throw new RequestException("No user found with given details.");
        }
        
        String token = new JwtTokenService().getToken(foundUser);
        foundUser.setToken(token);
        System.out.println("Token is "+token);
        return foundUser;
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public User changePassword(@RequestBody User user) {
        User foundUser = userRepo.getOne(user.getId());
        if(foundUser==null) throw new RuntimeException("User not found.");
        foundUser.setPassword(user.getPassword());
        String token = new JwtTokenService().getToken(foundUser);
        foundUser.setToken(token);
        return userRepo.save(foundUser);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/mail")
    public void testMail(@RequestParam("email") String email,@RequestParam("subject") String subject, @RequestParam("body") String body) {
        emailService.sendEmail(body, subject, email);
    }
    
    
}
