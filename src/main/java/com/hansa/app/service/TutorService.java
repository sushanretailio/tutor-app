/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import com.hansa.app.data.Tutor;
import com.hansa.app.repo.TutorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */

@Service
public class TutorService {
    
    @Autowired
    private TutorRepo  tutorRepo;
    
    
    public Tutor updateCredit(Long tutorId, int credit) {
        Tutor tutor = tutorRepo.getById(tutorId);
        tutor.setCredit(credit);
        return tutorRepo.save(tutor);
    }
}
