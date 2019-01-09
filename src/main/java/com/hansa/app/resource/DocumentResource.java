/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.DocumentType;
import com.hansa.app.error.RequestException;
import com.hansa.app.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sushant
 */

@RestController
@RequestMapping(path = "/document")
public class DocumentResource {
    
    
    @Autowired
    private S3Service s3Service;
    
    public String upload(@RequestParam("refId") Long refId, @RequestParam("type") String type , @RequestParam("documentType") DocumentType documentType
            ,  @RequestParam("file") MultipartFile file) {
        try {
           return s3Service.save(file.getBytes(), type, refId+"_"+type, documentType);
        } catch(Exception e) {
            throw new RequestException("Error while uplloading file "+e.getMessage());
        }
        
    }
    
}
