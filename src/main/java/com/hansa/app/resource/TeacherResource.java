/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;


import com.hansa.app.repo.TutorRepo;
import com.hansa.app.data.Tutor;
import com.hansa.app.data.User;
import com.hansa.app.error.RequestException;
import com.hansa.app.model.PagedResponse;
import com.hansa.app.repo.JobApplicationRepo;
import com.hansa.app.repo.ReviewRepo;
import com.hansa.app.repo.UserRepo;
import com.hansa.app.service.MailUtil;
import com.hansa.app.service.S3Service;
import com.hansa.app.service.TutorService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sushant
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tutor")
public class TeacherResource {
    
    @Autowired
    private TutorRepo tutorRepo;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private ReviewRepo reviewRepo;
    
    @Autowired
    private S3Service s3Service;
    
    @Autowired
    private TutorService service;
    
    @Autowired
    private JobApplicationRepo applicationRepo;
    
    @Autowired
    private MailUtil mailUtil;
    
    
    @RequestMapping(value = "/{id}/upload", method = RequestMethod.POST)
    public Boolean uploadPhoto(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String url = s3Service.save(bytes, "png", "tutor_"+id);
            System.out.println("Url "+url);
            return true;
        } catch(IOException | IllegalStateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    @RequestMapping(method = {RequestMethod.GET})
    public PagedResponse getTutors(@RequestParam(name="page", required=false) Integer page,@RequestParam(name="size", required = false) Integer size) {
        if(page==null) page=0;
        if(size==null) size=10;
        
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Tutor> pages =  tutorRepo.findAll(pageRequest);
        PagedResponse pagedTutor = new PagedResponse();
        pagedTutor.setNext(pages.hasNext());
        pagedTutor.setPage(pages.getNumber());
        pagedTutor.setSize(pages.getTotalPages());
        pagedTutor.setTotalSize(pages.getTotalElements());
        pagedTutor.setContents(pages.getContent());
        return pagedTutor;
    }
    
    
    public Tutor updateCredit(@PathVariable("id") Long id , int credit) {
        
        return service.updateCredit(id, credit);
    }
    
    
    @RequestMapping("/{id}")
    public Tutor get(@PathVariable("id") Long id) {
        Tutor tutor = tutorRepo.getById(id);
        if(tutor==null) {
            throw new RequestException("Tutor not found "+id);
        }
        tutor.setReviews(reviewRepo.getByTutor(id));
        
        String url = s3Service.get("tutor_"+tutor.getId(), "png");
        tutor.setImageUrl(url);
        tutor.getReviews().forEach(it-> it.setTutor(null));
        
        
        
        return tutor;
    }
    
    
    @RequestMapping(method = {RequestMethod.POST})
    public User save(@RequestBody Tutor tutor) {
        if(tutor.getMobile()==null || tutor.getMobile().isEmpty()) {
            throw new RequestException("Mobile number cant be empty");
        }
        
        tutor.setCredit(500);
        Tutor updated= tutorRepo.save(tutor);
        User user = new User();
        user.setRefId(updated.getId());
        user.setType("TUTOR");
        user.setUserId(updated.getMobile());
        user.setPassword(tutor.getMobile());
        userRepo.save(user);
        user.setDetail(updated);
        mailUtil.tutorRegister(updated);
        return user;
    }
    
    
    @GetMapping("/mail")
    public void testMailTutor(@RequestParam("email") Long tutorid) {
        Tutor t = tutorRepo.getById(tutorid);
        mailUtil.tutorRegister(t);
    }
    
    
}
