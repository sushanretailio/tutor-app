/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.Review;
import com.hansa.app.repo.ReviewRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant
 */
@RestController
@RequestMapping("/review")
public class ReviewResource {
    
    @Autowired
    private ReviewRepo reviewRepo;
    
    @RequestMapping(method = RequestMethod.POST)
    public Review save(@RequestBody Review review) {
        return reviewRepo.save(review);
    }
    
    @RequestMapping(value = "/tutor/{id}",method = RequestMethod.GET)
    public List<Review> getByTutor(@PathVariable("tutorId") Long tutorId) {
        return reviewRepo.getByTutor(tutorId);
    }
    
    @RequestMapping(value = "/student/{id}",method = RequestMethod.GET)
    public List<Review> getByStudent(@PathVariable("studentId") Long studentId) {
        return reviewRepo.getByStudent(studentId);
    }
    
}
