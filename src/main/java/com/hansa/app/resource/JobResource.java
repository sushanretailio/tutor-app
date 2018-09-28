/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.Job;
import com.hansa.app.data.JobApplication;
import com.hansa.app.data.JobStatus;
import com.hansa.app.data.Tutor;
import com.hansa.app.data.UserRole;
import com.hansa.app.error.RequestException;
import com.hansa.app.model.PagedResponse;
import com.hansa.app.repo.JobApplicationRepo;
import com.hansa.app.repo.JobRepo;
import com.hansa.app.repo.TutorRepo;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
        if(job.getStatus()== JobStatus.CANCELLED || job.getStatus()==JobStatus.CLOSED) {
            throw new RequestException("Job is closed.");
        }
        int count = applicationRepo.getApplicationCount(id);
        if(count>=10) {
            throw new RequestException("Job application limit exceeded.");
        }
        
        JobApplication application=  applicationRepo.getByJobId(id, tutorId);
        if(application!=null) {
            throw new RequestException("Tutor already applied for this job "+tutorId);
        }
        
        
        JobApplication app = new JobApplication();
        app.setJob(job);
        app.setStatus("APPLIED");
        app.setUpdatedOn(LocalDateTime.now());
        Tutor tutor = tutorRepo.getById(tutorId);
        if(tutor==null) {
            throw new RequestException("Tutor not found "+tutorId);
        }
        app.setTutor(tutorRepo.getById(tutorId));
        return applicationRepo.save(app);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value ="/{id}status" ,method = RequestMethod.PUT)
    public Job updateStatus(@RequestHeader(name = "userId", required = false) Long userId, @RequestHeader("role") UserRole role,@PathVariable("id") Long id, @RequestParam("status") JobStatus status) {
        if(role==UserRole.ANONIMOUS || userId==null) {
            throw new RequestException("User not logged in");
        }
        Job job = jobRepo.getOne(id);
        job.setStatus(status);
        return jobRepo.save(job);
    }
    
    
    @RequestMapping("/{id}")
    public Job get(@PathVariable("id") Long id) {
        Job job = jobRepo.get(id);
        List<JobApplication> applicatios= applicationRepo.getByJobId(id);
        job.setApplications(applicatios);
        return job;
        
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public PagedResponse getJobs(@RequestParam(name = "page",required = false) Integer page,@RequestParam(name="size",required = false) Integer size ) {
        if(page==null) page=0;
        if(size==null) size=10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Job> jobs = jobRepo.findAll(pageRequest);
        PagedResponse resp = new PagedResponse();
        resp.setContents(jobs.getContent());
        resp.setNext(jobs.hasNext());
        resp.setPage(jobs.getTotalPages());
        resp.setTotalSize(jobs.getTotalElements());
        return resp;
    }
}
