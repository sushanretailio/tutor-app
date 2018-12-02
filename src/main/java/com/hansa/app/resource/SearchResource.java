/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.Gender;
import com.hansa.app.data.Tutor;
import com.hansa.app.search.SearchParam;
import com.hansa.app.search.SearchSpecification;
import com.hansa.app.repo.TutorRepo;
import com.hansa.app.search.SearchDB;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/search")
public class SearchResource {

    @Autowired
    private SearchDB dB;

    @GetMapping()
    public List<Tutor> search(@RequestParam(name = "city", required = false) String city, @RequestParam(name = "zip", required = false) String zip,
            @RequestParam(name = "gender", required = false) Gender gender, @RequestParam(name = "stdClass", required = false) Integer stdClass,
            @RequestParam(name = "subject", required = false) Integer subject, @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size) {
        SearchParam search = new SearchParam();
        search.setGender(gender);
        search.setStdClass(stdClass);
        search.setSubject(subject);
        search.setZip(zip);
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }

        SearchSpecification spec = new SearchSpecification(search);
        return dB.search(search);
        
    }
}
