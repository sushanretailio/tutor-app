/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.Job;
import com.hansa.app.data.JobApplication;
import com.hansa.app.data.JobStatus;
import com.hansa.app.repo.JobApplicationRepo;
import com.hansa.app.repo.JobRepo;
import com.hansa.app.repo.TutorRepo;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant
 */

@RestController
@RequestMapping("/job")
public class JobResource {
    
    @Autowired
    private JobRepo jobRepo;
    
    @Autowired
    private JobApplicationRepo applicationRepo;
    
    @Autowired
    private TutorRepo tutorRepo;
    
    @RequestMapping(method = RequestMethod.POST)
    public Job post(@RequestBody Job job) {
        job.setStatus(JobStatus.OPEN);
        job.setCreatedOn(LocalDateTime.now());
        return jobRepo.save(job);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value ="/{id}/apply" ,method = RequestMethod.PUT)
    public JobApplication apply(@PathVariable("id") Long id, @RequestParam("tutorId") Long tutorId) {
        Job job = jobRepo.getOne(id);
        if(job.getStatus().equals("CLOSED") || job.getStatus().equals("CANCELLED")) {
            throw new RuntimeException("Job is closed.");
        }
        int count = applicationRepo.getApplicationCount(id);
        if(count>=10) {
            throw new RuntimeException("Job application limit exceeded.");
        }
        JobApplication app = new JobApplication();
        app.setJob(job);
        app.setStatus("APPLIED");
        app.setUpdatedOn(LocalDateTime.now());
        app.setTutor(tutorRepo.getById(tutorId));
        return applicationRepo.save(app);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value ="/{id}status" ,method = RequestMethod.PUT)
    public Job updateStatus(@PathVariable("id") Long id, @RequestParam("status") JobStatus status) {
        Job job = jobRepo.getOne(id);
        job.setStatus(status);
        return jobRepo.save(job);
    }
}
