/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.Job;
import com.hansa.app.data.JobApplication;
import com.hansa.app.data.JobStatus;
import com.hansa.app.data.UserRole;
import com.hansa.app.error.RequestException;
import com.hansa.app.model.PagedResponse;
import com.hansa.app.model.TutorCredit;
import com.hansa.app.repo.JobApplicationRepo;
import com.hansa.app.repo.JobRepo;
import com.hansa.app.repo.TutorRepo;
import com.hansa.app.service.TutorService;
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

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.PATCH,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/admin")
public class AdminResource {
    
    
    
    
    @Autowired
    private JobRepo jobRepo;
    
    @Autowired
    private JobApplicationRepo applicationRepo;
    
    @Autowired
    private TutorRepo tutorRepo;
    
    @Autowired
    private TutorService tutorService;
    
    
    
    @RequestMapping(value ="/{id}/status" ,method = RequestMethod.PUT)
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
        applicatios.forEach(it-> it.setJob(null));
        job.setApplications(applicatios);
        return job;
        
    }
    
    @RequestMapping(path =  "/credit",method = RequestMethod.POST)
    public void updateCredit(@RequestBody List<TutorCredit> credits) {
        tutorService.updateCredit(credits);
    }
    
    
    
    @RequestMapping(method = RequestMethod.GET)
    public PagedResponse getJobs(@RequestParam(name = "page",required = false) Integer page,@RequestParam(name="size",required = false) Integer size ) {
        if(page==null) page=0;
        if(size==null) size=10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Job> jobs = jobRepo.findAll(pageRequest);
        jobs.forEach(it-> it.setApplications(null));
        PagedResponse resp = new PagedResponse();
        
        resp.setContents(jobs.getContent());
        resp.setNext(jobs.hasNext());
        resp.setPage(jobs.getTotalPages());
        resp.setTotalSize(jobs.getTotalElements());
        return resp;
    }
}
