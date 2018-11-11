/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import com.hansa.app.data.Gender;
import com.hansa.app.data.Tutor;
import com.hansa.app.error.RequestException;
import com.hansa.app.model.TutorCredit;
import com.hansa.app.repo.TutorRepo;
import java.util.List;
import javax.transaction.Transactional;
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
    
    final int APPLY_CHARGE=50;
    
    
    
    @Transactional
    public void updateCredit(List<TutorCredit> llist) {
        for(TutorCredit tc : llist) {
            tutorRepo.updateCredit(tc.getTutorId(), tc.getCredit());
        }
    }
    
    public Tutor apply(Long tutorId) {
        Tutor tutor = tutorRepo.getById(tutorId);
        if(tutor==null) {
            throw new RequestException("Tutor not found "+tutorId);
        }
        if(tutor.getCredit()<APPLY_CHARGE) {
            throw new RequestException("Insufficient Credit "+tutorId);
        }
        tutor.setCredit(tutor.getCredit()-APPLY_CHARGE);
        return tutorRepo.save(tutor);
    }
    
    
    public List<Tutor> findAll(String subject, Gender gender) {
        return tutorRepo.find();
    }
    
    public Tutor updateCredit(Long tutorId, int credit) {
        Tutor tutor = tutorRepo.getById(tutorId);
        tutor.setCredit(credit);
        return tutorRepo.save(tutor);
    }
}
