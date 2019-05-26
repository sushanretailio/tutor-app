/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.Faq;
import com.hansa.app.data.FaqCategory;
import com.hansa.app.repo.FaqRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant
 */
@RequestMapping("/faq")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.PATCH,RequestMethod.POST,RequestMethod.PUT})
@RestController

public class FaqResource {

    
    
    @Autowired
    private FaqRepo faqRepo;
    
    @GetMapping()
    public List<Faq> get(@RequestParam("category") FaqCategory category) {
        return faqRepo.getByCategory(category);
    }
    
    @PostMapping()
    private List<Faq> save(@RequestBody List<Faq> faqList) {
        return faqRepo.saveAll(faqList);
    }
}
