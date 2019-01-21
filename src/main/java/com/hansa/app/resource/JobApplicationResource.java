/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.ApplicationStatus;
import com.hansa.app.data.Job;
import com.hansa.app.data.JobApplication;
import com.hansa.app.data.JobStatus;
import com.hansa.app.error.RequestException;
import com.hansa.app.model.PagedResponse;
import com.hansa.app.repo.JobApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/jobApplication")
public class JobApplicationResource {
    
    @Autowired
    private JobApplicationRepo applicationRepo;
    
    
    
    @RequestMapping(path="/{id}/contact",method = RequestMethod.PUT)
    public JobApplication updateContact(@PathVariable("id") Long id, @PathVariable("enabled") boolean enabled ) {
        JobApplication ja= applicationRepo.getById(id);
        if(ja.getJob()!=null) {
            Job job = ja.getJob();
            if(job.getStatus()==JobStatus.CANCELLED ||  job.getStatus()==JobStatus.CLOSED) {
            throw new RequestException("Job is Cancelled / Closed");
        }
        }
        if(ja.getStatus().equals("CACELLED") ||  ja.getStatus().equals("CLOSED")) {
            throw new RequestException("Application is Cancelled / Closed");
        }
        ja.setContactEnabled(enabled);
        return applicationRepo.save(ja);
    }
   
    
    @RequestMapping(path="/{id}",method = RequestMethod.GET)
    public PagedResponse getJobApplication(@PathVariable(name="id") Long tutorId, @RequestParam(name = "page",required = false) Integer page,@RequestParam(name="size",required = false) Integer size ) {
        if(page==null) page=0;
        if(size==null) size=10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<JobApplication> jobs = applicationRepo.getAllByTutorId(tutorId,pageRequest);
        jobs.getContent().forEach(it-> it.getJob().setApplications(null));
        jobs.getContent().forEach(it-> it.setTutor(null));
        PagedResponse resp = new PagedResponse();
        resp.setContents(jobs.getContent());
        resp.setNext(jobs.hasNext());
        resp.setPage(jobs.getTotalPages());
        resp.setTotalSize(jobs.getTotalElements());
        return resp;
    }
    
    @RequestMapping(path="/{id}/status",method = RequestMethod.PUT)
    public JobApplication updateStatus(@PathVariable("id") Long id, @RequestParam("status") ApplicationStatus status) {
        JobApplication ja= applicationRepo.getById(id);
        if(ja.getJob()!=null) {
            Job job = ja.getJob();
            if(job.getStatus()==JobStatus.CANCELLED ||  job.getStatus()==JobStatus.CLOSED) {
            throw new RequestException("Job is Cancelled / Closed");
        }
        }
        if(ja.getStatus().equals("CACELLED") ||  ja.getStatus().equals("CLOSED")) {
            throw new RequestException("Application is Cancelled / Closed");
        }
        ja.setStatus(status.name());
        return applicationRepo.save(ja);
    }
    
    
}
