/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import com.hansa.app.data.JobStatusData;
import com.hansa.app.repo.JobStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */

@Service
public class JobService {
    
    @Autowired
    private JobStatusRepo jobStatusRepo;
    
    
    public void updateStatus(JobStatusData data) {
        jobStatusRepo.save(data);
    }
}
