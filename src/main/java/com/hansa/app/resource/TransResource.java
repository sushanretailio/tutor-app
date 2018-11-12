/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.TransactionData;
import com.hansa.app.model.PagedResponse;
import com.hansa.app.service.TransService;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transaction")
public class TransResource {
    
    @Autowired
    private TransService transService;
    
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody TransactionData data) {
         transService.save(data);
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public PagedResponse getByTutor(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        
        if(page==null) page = 0;
        if(size==null) size =10;
        
        return transService.get(id, page, size);
    }
    
    
    
}
