/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;


import com.hansa.app.repo.TutorRepo;
import com.hansa.app.data.Tutor;
import com.hansa.app.model.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/tutor")
public class TeacherResource {
    
    @Autowired
    private TutorRepo tutorRepo;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = {RequestMethod.GET})
    public PagedResponse getTutors(@RequestParam(name="page", required=false) Integer page,@RequestParam(name="size", required = false) Integer size) {
        if(page==null) page=0;
        if(size==null) size=10;
        
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Tutor> pages =  tutorRepo.findAll(pageRequest);
        PagedResponse pagedTutor = new PagedResponse();
        pagedTutor.setNext(pages.hasNext());
        pagedTutor.setSize(pages.getTotalPages());
        pagedTutor.setSize(pages.getNumberOfElements());
        pagedTutor.setContents(pages.getContent());
        return pagedTutor;
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = {RequestMethod.POST})
    public Tutor save(@RequestBody Tutor tutor) {
        return tutorRepo.save(tutor);
    }
    
    
}
